package View;

import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.control.MenuBar;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.control.ColorPicker;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.layout.VBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.shape.Rectangle;

import javafx.scene.text.*;
import javafx.event.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.image.ImageView;

/**
 * @author Ryan Piersma
 */
public class SetupView extends BorderPane {

    public static final String PEN_STRING = "Pen color is now:";
    public static final String BACKGROUND_STRING = "Background color is now:";
    public static final Color PEN_DEFAULT_COLOR = Color.BLACK;
    public static final String DEFAULT_LANGUAGE = "English";
    public static final double VAR_BOX_HEIGHT = 250;
    public static final double FUNC_BOX_HEIGHT = 100;
    public static final double BOX_WIDTH = 80;
    public static final double IMAGE_CHOOSE_LOWER_X = 57;
    public static final double IMAGE_CHOOSE_HIGHER_X = 97;
    public static final double IMAGE_CHOOSE_LOWER_Y = 565;
    public static final double IMAGE_CHOOSE_HIGHER_Y = 605;

    private static final String LANGUAGE_LIST_FILEPATH = "data//examples//langauges//languageList.txt";
    private List<String> languages = new ArrayList<>();
    private String currLanguage = DEFAULT_LANGUAGE;
    private Color penColor = PEN_DEFAULT_COLOR;
    private Color backgroundColor;
    private Stage primaryStage;
    private ImageView currTurtleImage = new ImageView();
    private File imageFile = new File("slogo_team06\\data\\images\\illuminati.png");
    private Text penText;
    private Text langText;
    private Text picText;
    private MainView mainView;
    private TextArea variableText = new TextArea();
    private TextArea functionText = new TextArea();

    public SetupView(MainView view) {
        this.mainView = view;
    }

    public void setStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public Text getPenText() {
        return penText;
    }

    public Text getLangText() {
        return langText;
    }


    public MenuBar initializeMenuBar() {
        MenuBar menus = new MenuBar();

        Menu langMenu = new Menu("Languages");
        populateLanguageMenu(langMenu);
        Menu imageMenu = new Menu("Image Select");
        populateImageMenu(imageMenu);
        Menu colorMenu = new Menu("Color");
        populateColorMenu(colorMenu);
        Menu saveloadMenu = new Menu("Save/Load");
        populateSaveLoadMenu(saveloadMenu);
        menus.getMenus().addAll(langMenu, imageMenu, colorMenu, saveloadMenu);
        return menus;
    }

    public void populateLanguageMenu(Menu langMenu) {
        try {
            int languageSet = 0;
            BufferedReader readLang = new BufferedReader(new FileReader(LANGUAGE_LIST_FILEPATH));
            String currLang;
            while ((currLang = readLang.readLine()) != null) {
                languages.add(currLang);
                final int languageSetter = languageSet;
                MenuItem langLine = new MenuItem(currLang);
                langLine.setOnAction(new EventHandler<ActionEvent>() {
                    public void handle(ActionEvent t) {
                        currLanguage = languages.get(languageSetter);
                        changeLanguage(langText);
                        getMainView().getUserScreen().setLanguage(currLanguage);
                    }
                });
                langMenu.getItems().add(langLine);
                languageSet++;
            }
        } catch (IOException ioE) {
            System.err.println("The language file was not found or there was an error reading the language file.");
        }
    }

    public void populateColorMenu(Menu colorMenu) {
           /* MenuItem colorChoose = new MenuItem("Choose Color with Manual RGB Entry");
            Dialog<String> colorDialog = setupColorDialogBox();
            colorMenu.setOnAction(new EventHandler<ActionEvent>() {
               public void handle(ActionEvent e)
               {
                   colorDialog.showAndWait();//.filter(response -> response == ButtonType.OK).ifPresent(response -> formatSystem());
;               }
            });*/

        ColorPicker pickPenColor = setupPenColorPicker(PEN_STRING);
        MenuItem colorPicker = new MenuItem("Choose Pen Color", pickPenColor);
        colorMenu.getItems().add(colorPicker);

        ColorPicker pickBackgroundColor = setupBackgroundColorPicker(BACKGROUND_STRING);
        MenuItem backgroundPicker = new MenuItem("Choose Background Color", pickBackgroundColor);
        colorMenu.getItems().add(backgroundPicker);
    }

    public void populateImageMenu(Menu imageMenu) {
        FileChooser chooseImage = setupImageFileChooser();
        MenuItem fileChoose = new MenuItem("Choose Image File");
        imageMenu.getItems().add(fileChoose);
        fileChoose.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent t) {
                handleImageClick(chooseImage);
            }
        });

    }

    public void populateSaveLoadMenu(Menu saveloadMenu)
    {
        MenuItem saveFile = new MenuItem("Begin Save");
        saveFile.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent t) {
                    getMainView().getUserScreen().setSaveState(true);
                    System.out.println("Save: " + getMainView().getUserScreen().getSaveState());
            }
        });
        MenuItem loadFile = new MenuItem("Begin Load");
        loadFile.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent t) {
                getMainView().getUserScreen().setLoadState(true);
                System.out.println("Load: " + getMainView().getUserScreen().getLoadState());
            }
        });
        saveloadMenu.getItems().addAll(saveFile, loadFile);
    }

    public Dialog<String> setupColorDialogBox() {
        Dialog<String> rgbEnter = new Dialog<>();
        Label label1 = new Label("R: ");
        Label label2 = new Label("G: ");
        Label label3 = new Label("B: ");

        return rgbEnter;
    }

    public ColorPicker setupPenColorPicker(String menuLabel) {
        ColorPicker pickPenColor = new ColorPicker();
        pickPenColor.setOnAction(new EventHandler() {
            public void handle(Event t) {
                setPenColor(pickPenColor.getValue());
                System.out.println(menuLabel + " " + penColor);
                changeColor(penText);
                getMainView().getUserScreen().changePenColor(getPenColor());
            }
        });

        return pickPenColor;
    }

    public ColorPicker setupBackgroundColorPicker(String menuLabel) {
        ColorPicker pickBackgroundColor = new ColorPicker();
        pickBackgroundColor.setOnAction(new EventHandler() {
            public void handle(Event t) {
                backgroundColor = pickBackgroundColor.getValue();
                System.out.println(menuLabel + " " + backgroundColor);
                getMainView().getUserScreen().changeUserScreenColor(backgroundColor);
            }
        });

        return pickBackgroundColor;
    }

    public FileChooser setupImageFileChooser() {
        FileChooser imageChoose = new FileChooser();
        imageChoose.setTitle("Choose an image file to be displayed!");
        imageChoose.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif", "*.jpeg"));
        return imageChoose;
    }

    public VBox setupDisplayBoxes() {
        VBox superBox = new VBox();
        superBox.setPrefWidth(100);
        superBox.setSpacing(20);
        setupVariablesBox(superBox);
        setupFunctionsBox(superBox);
        penText = displayVariableText("Pen color", penColor.toString(), superBox);
        langText = displayVariableText("Language", currLanguage, superBox);
        picText = displayVariableText("Current Turtle Image ", "", superBox);
        addVboxEventHandler(superBox);
        superBox.getChildren().add(currTurtleImage);
        return superBox;
    }

    private void addVboxEventHandler(VBox superBox) {
        superBox.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if ((mouseEvent.getY() >= IMAGE_CHOOSE_LOWER_Y && mouseEvent.getY() <= IMAGE_CHOOSE_HIGHER_Y) && (mouseEvent.getX() >= IMAGE_CHOOSE_LOWER_X && mouseEvent.getX() <= IMAGE_CHOOSE_HIGHER_X)) {
                    FileChooser imageChoose = setupImageFileChooser();
                    handleImageClick(imageChoose);
                }
            }
        } );
    }

    private void handleImageClick(FileChooser imageChoose) {
        imageFile = imageChoose.showOpenDialog(primaryStage);
        if (imageFile != null) {
            getMainView().getUserScreen().getMainTurtleView().setTurtleImage(imageFile.toURI().toString());
            getMainView().getUserScreen().setTurtleView(new ImageView(imageFile.toURI().toString()));
        }
    }

    public VBox setupVariablesBox(VBox displayBox) {
        displayBox.getChildren().add(setupBoxTitle("Stored Variables", 16));

        ScrollPane variableBox = new ScrollPane();
        variableBox.setPrefSize(BOX_WIDTH, VAR_BOX_HEIGHT);
        variableBox.setContent(variableText);

        displayBox.getChildren().add(variableBox);
        return displayBox;
    }

    public VBox setupFunctionsBox(VBox displayBox) {
        displayBox.getChildren().add(setupBoxTitle("User Defined Functions", 14));

        ScrollPane functionBox = new ScrollPane();
        functionBox.setPrefSize(BOX_WIDTH, FUNC_BOX_HEIGHT);
        functionBox.setContent(functionText);

        displayBox.getChildren().add(functionBox);
        return displayBox;
    }

    public Text displayVariableText(String varName, String varValue, VBox displayBox) {
        Text addText = setupBoxTitle(varName + ":  " + varValue, 12);
        displayBox.getChildren().add(addText);
        return addText;
    }

    public Text setupBoxTitle(String title, int fontSize) {
        Text newText = new Text(title);
        newText.setFont(Font.font("Verdana", FontWeight.BOLD, fontSize));
        newText.setTextAlignment(TextAlignment.CENTER);
        return newText;
    }

    public void changeLanguage(Text langText) {
        langText.setText("Language: " + currLanguage);
    }

    public void changeColor(Text colorText)
    {
        colorText.setText("Pen color: " + penColor);
    }

    public MainView getMainView()
    {
        return this.mainView;
    }

    public Color getPenColor()
    {
        return this.penColor;
    }

    public TextArea getVariableText()
    {
        return this.variableText;
    }

    public TextArea getFunctionText()
    {
        return this.functionText;
    }

    public void setPenColor(Color newColor) {
      this.penColor = newColor;
    }

}
