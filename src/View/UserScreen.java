package View;

import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Line;
import javafx.geometry.Insets;
import javafx.scene.input.MouseEvent;
import javafx.event.EventHandler;

import java.util.HashMap;

/**
 * @author Ethan Ho (primary)
 * @author Ryan Piersma
 */
public class UserScreen extends Pane {

    public static final double IMAGE_Y_OFFSET = 20;
    public static final double IMAGE_X_OFFSET = 10;

    public static final double TURTLEVIEW_X_POSITION = 675.0;
    public static final double TURTLEVIEW_Y_POSITION = 565.0;
    public static final double TURTLEVIEW_SCALE_SIZE = 40;

    private TurtleView mainTurtleView;
    private Color penColor;
    private String language = "English";
    private HashMap<Double, TurtleView> turtleViewMap = new HashMap<>();
    private TextArea functionArea = new TextArea();
    private TextArea variableArea = new TextArea();
    private VBox rightbar;
    private double graphics_X_OFFSET = 0;
    private double graphics_Y_OFFSET = 0;
    private double orientation_OFFSET = 0;
    private boolean save;
    private boolean load;
    private double XInit;
    private double YInit;
    private ImageView turtleView;
//    private ArrayList<Turtle> turtleList = new ArrayList<>();

    public UserScreen(){
        super();
        mainTurtleView = new TurtleView();
        setStyle("-fx-padding: 10;" +
                "-fx-border-style: solid inside;" +
                "-fx-border-width: 10;" +
                "-fx-border-insets: 5;" +
                "-fx-border-radius: 5;" +
                "-fx-border-color: black;");
    }

    public void clearScreen(){
        getChildren().removeAll(getChildren());
    }

    public void initializeScreen(double x, double y){
        mainTurtleView.setLocation(x, y);
        XInit = x;
        YInit = y;
        ImageView turtleSprite = mainTurtleView.getSprite();
        getChildren().add(turtleSprite);
        turtleViewMap.put(1.0, mainTurtleView);
        initializeTurtleView(turtleSprite);
    }

    /**
     * ADDED TO IMPLEMENT EXTRA FEATURE ADDITION ASSIGNMENT IN VOOGASALAD
     * @param turtleSprite image that the turtleview is connected to (from main turtle sprite)
     */
    private void initializeTurtleView(ImageView turtleSprite) {
        this.turtleView = new ImageView(turtleSprite.getImage());
        turtleView.setX(TURTLEVIEW_X_POSITION);
        turtleView.setY(TURTLEVIEW_Y_POSITION);
        turtleView.setFitHeight(TURTLEVIEW_SCALE_SIZE);
        turtleView.setFitWidth(TURTLEVIEW_SCALE_SIZE);
        getChildren().add(turtleView);
    }

    public TurtleView getMainTurtleView(){
        return this.mainTurtleView;
    }

    public HashMap getTurtleViewMap(){
        return this.turtleViewMap;
    }

    public void addTurtle(Double id){
        TurtleView turtleView = new TurtleView();
        turtleView.setLocation(XInit, YInit);
//        turtleView.setLocation(100, 100);
        ImageView turtleSprite = turtleView.getSprite();
        getChildren().add(turtleSprite);
        turtleViewMap.put(id, turtleView);
    }

    public void changeUserScreenColor(Color newColor)
    {
        this.setBackground(new Background(new BackgroundFill(newColor, CornerRadii.EMPTY, Insets.EMPTY)));
    }

    public void changePenColor(Color newColor)
    {
        this.penColor = newColor;
    }

    public void drawLine(double oldX, double oldY, double newX, double newY)
    {
        Line drawMe = new Line();
        drawMe.setStartX(oldX + IMAGE_X_OFFSET);
        drawMe.setStartY(oldY + IMAGE_Y_OFFSET);
        drawMe.setEndX(newX + IMAGE_X_OFFSET);
        drawMe.setEndY(newY + IMAGE_Y_OFFSET);
        drawMe.setStroke(this.penColor);
        this.getChildren().add(drawMe);
    }

    public String getLanguage()
    {
        return this.language;
    }

    public void setLanguage(String language)
    {
        this.language = language;
    }

    public boolean getLoadState()
    {
        return this.load;
    }

    public boolean getSaveState()
    {
        return this.save;
    }

    public void setSaveState(boolean newSave)
    {
        this.save = newSave;
    }

    public void setLoadState(boolean newLoad)
    {
        this.load = newLoad;
    }

    public ImageView getTurtleView() {
        return this.turtleView;
    }

    public void setTurtleView(ImageView newTurtleImage) {
        getChildren().remove(turtleView);
        this.turtleView = newTurtleImage;
        initializeTurtleView(newTurtleImage);
    }

}
