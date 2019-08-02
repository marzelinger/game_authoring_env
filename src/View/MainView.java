package View;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.control.TextArea;

public class MainView{
    private static final Integer SCREEN_SIZE = 800;
    private static final Integer BUTTON_WIDTH = 60;
    private static final Integer BUTTON_HEIGHT = 2;
    private static final Integer BUTTON_BOX_SPACING = 8;
    private static final Integer BUTTON_BOX_PADDING = 5;

    private Scene myScene;
    private SetupView mainGUI;
    private UserScreen userScreen;
    private BorderPane commandLine;
    private UserInput userInput;
    private UserHistory userHistory;
    private HBox buttonBox;
    private VBox runBox;
    private VBox historyBox;
    private TextArea varText;
    private TextArea funcText;

    private String output;
    private boolean inputState = false;

    public MainView() {
        mainGUI = new SetupView(this);
        userScreen = new UserScreen();
        commandLine = new BorderPane();
        userInput = new UserInput();
        userHistory = new UserHistory();
        buttonBox = new HBox(BUTTON_BOX_SPACING);
        buttonBox.setPadding(new Insets(BUTTON_BOX_PADDING));
        runBox = new VBox();
        historyBox = new VBox();
        myScene = initializeScene(SCREEN_SIZE, SCREEN_SIZE);
    }

    public Scene getMyScene(){
        return myScene;
    }

    public String getOutput(){
        return output;
    }

    public UserScreen getUserScreen(){
        return userScreen;
    }

    public boolean getInputState(){
        return inputState;
    }

    public void setInputState(boolean inputState){
        this.inputState = inputState;
    }

    public void setSetupView(SetupView setup)
    {
        this.mainGUI = setup;
    }

    public SetupView getSetupView()
    {
        return this.mainGUI;
    }

    public UserHistory getUserHistory()
    {
        return this.userHistory;
    }

    public Scene initializeScene(double width, double height){
        createCommandPrompt();
        mainGUI.setBottom(commandLine);
        mainGUI.setCenter(userScreen);
//        userScreen.initializeScreen(width, height);
        mainGUI.setTop(mainGUI.initializeMenuBar());
        mainGUI.setRight(mainGUI.setupDisplayBoxes());
        varText = mainGUI.getVariableText();
        funcText = mainGUI.getFunctionText();
        Scene scene = new Scene(mainGUI, width, height);
        scene.setOnKeyPressed(e -> handleKeyInput(e.getCode()));
        return scene;
    }

    private void createCommandPrompt() {
        Label title = new Label("Command history");
        commandLine.setTop(title);
        commandLine.setCenter(userHistory);
        commandLine.setBottom(userInput);
        createHistoryButtons();
        createRunButtons();
        buttonBox.getChildren().addAll(runBox, historyBox);
        commandLine.setRight(buttonBox);
    }

    private void createHistoryButtons() {
        Button prevButton = new Button("Prev");
        Button nextButton = new Button("Next");
        prevButton.setPrefSize(BUTTON_WIDTH, BUTTON_HEIGHT);
        nextButton.setPrefSize(BUTTON_WIDTH, BUTTON_HEIGHT);
        prevButton.setOnAction(e -> {
            userInput.clearText();
            String instruction = userHistory.cycleHistory(true);
            userInput.setText(instruction);
        });
        nextButton.setOnAction(e -> {
            userInput.clearText();
            String instruction = userHistory.cycleHistory(false);
            userInput.setText(instruction);
        });
        historyBox.getChildren().addAll(prevButton, nextButton);
    }

    private void createRunButtons() {
        Button runButton = new Button("Run");
        Button clearButton = new Button("Clear");
        runButton.setPrefSize(BUTTON_WIDTH, BUTTON_HEIGHT);
        clearButton.setPrefSize(BUTTON_WIDTH, BUTTON_HEIGHT);
        runButton.setOnAction(e -> activateRunButton());
        clearButton.setOnAction(e -> {
            userInput.clearText();
        });
        runBox.getChildren().addAll(runButton, clearButton);
    }

    private void activateRunButton(){
        String instruction = userInput.getText();
        output = instruction;
        Text newInstruction = createHistoryText(instruction);
        userHistory.updateUserHistory(newInstruction);
        userInput.clearText();
        setInputState(true);
    }

    private void handleKeyInput(KeyCode code) {
        if(code == KeyCode.ENTER){
            activateRunButton();
        }
    }

    public Text createHistoryText(String instruction){
        Text newInstruction = new Text();
        newInstruction.setFont(Font.font("Monospaced", 14));
        newInstruction.setText(instruction);
        newInstruction.setOnMouseClicked(e -> {
            output = newInstruction.getText();
            userHistory.updateUserHistory(createHistoryText(output));
            setInputState(true);
        });
        return newInstruction;
    }

    public TextArea getVarText()
    {
        return this.varText;
    }

    public TextArea getFuncText()
    {
        return this.funcText;
    }
}
