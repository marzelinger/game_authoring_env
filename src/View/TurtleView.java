package View;

import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import java.util.Arrays;
import java.util.List;

public class TurtleView {
    private ImageView TURTLE;
    private String TURTLE_IMAGE = "file:resources/images/turtle.png";
    private double id;
    private boolean penStatus = true;
    private boolean activeState;

    private static final Integer TURTLE_SIZE = 20;

    public TurtleView(){
        TURTLE = new ImageView(TURTLE_IMAGE);
        TURTLE.setOnMouseClicked(e -> {
            activeState = !activeState;
        });
        TURTLE.setFitWidth(TURTLE_SIZE);
        TURTLE.setFitHeight(TURTLE_SIZE);
    }

    public ImageView getSprite(){
        return TURTLE;
    }

    public void setLocation(double x, double y){
        TURTLE.setX(x);
        TURTLE.setY(y);
    }

    public List<Double> getLocation(){
        List<Double> location = Arrays.asList(TURTLE.getX(), TURTLE.getY());
        return location;
    }

    public boolean getActiveState(){
        return activeState;
    }

    public void setActiveState(boolean activeState){
        this.activeState = activeState;
    }

    public void setTurtleImage(String filePath)
    {
        TURTLE.setImage(new Image(filePath));
    }

}
