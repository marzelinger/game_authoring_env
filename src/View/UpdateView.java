package View;

import Compiler.TokenParser.Nodes.Node;
import javafx.scene.image.ImageView;
import javafx.geometry.Point2D;
import Compiler.TurtleStatus;
import javafx.scene.control.TextArea;

import java.util.List;
import java.util.Map;

/**
 * @author Ryan Piersma
 */
public class UpdateView {
    private MainView mainAccess;
    private SetupView setupAccess;
    private double X_OFFSET;
    private double Y_OFFSET;


    public static final double ANGLE_OFFSET = 90.0;
    public static final int CORRECT_ANGLE_DIRECTION = -1;
    public static final double X_LOW_BOUND = -300.0;
    public static final double Y_LOW_BOUND = -300.0;
    public static final double X_HI_BOUND = 300.0;
    public static final double Y_HI_BOUND = 300.0;
    public static final double IMAGE_SIZE = 20;
    public static final double BORDER_WIDTH = 10;

    public UpdateView(MainView mainAccess, SetupView setupAccess) {
        this.mainAccess = mainAccess;
        this.setupAccess = setupAccess;
        this.X_OFFSET = (mainAccess.getUserScreen().getWidth())/2.0;
        this.Y_OFFSET = (mainAccess.getUserScreen().getHeight())/2.0;
    }

    public void updateLoop(TurtleView turtleView, TurtleStatus newStatus, boolean turtleState, double xOffset, double yOffset, Map<String, List<Node>> functions, Map<String, List<Node>> variables)

    {
        if (turtleState){
            if (newStatus.getPenStatus())
            {
                Point2D boundsCheckTurtle = turtleBoundsCheck(newStatus.getLocation());
                mainAccess.getUserScreen().drawLine(turtleView.getSprite().getX(), turtleView.getSprite().getY(), boundsCheckTurtle.getX() + xOffset, boundsCheckTurtle.getY() + yOffset);
                newStatus.setLocation(boundsCheckTurtle);
            }
            displayTurtle(turtleView.getSprite(), newStatus, xOffset, yOffset);
        }
        updateStoredVariables(variables);
        updateUserFunctions(functions);
    }

    public void displayTurtle(ImageView turtleImageView, TurtleStatus newStatus, double xOffset, double yOffset) {

        turtleImageView.setX(newStatus.getLocation().getX() + xOffset);
        turtleImageView.setY(newStatus.getLocation().getY() +  yOffset);
        turtleImageView.setRotate(newStatus.getOrientation()*CORRECT_ANGLE_DIRECTION + ANGLE_OFFSET);

        if (!newStatus.getVisible())
        {
            turtleImageView.setOpacity(0);
        }
        else
        {
            turtleImageView.setOpacity(1);
        }
    }

    public Point2D turtleBoundsCheck(Point2D inputCoordinates)
    {
        double xCoor, yCoor;
        if (inputCoordinates.getX() >= X_HI_BOUND)
        {
            xCoor = X_HI_BOUND - IMAGE_SIZE - BORDER_WIDTH;
        }

        else if (inputCoordinates.getX() <= X_LOW_BOUND)
        {
            xCoor = X_LOW_BOUND + BORDER_WIDTH;
        }
        else
        {
            xCoor = inputCoordinates.getX();
        }

        if (inputCoordinates.getY() >= Y_HI_BOUND)
        {
            yCoor = Y_HI_BOUND - IMAGE_SIZE - BORDER_WIDTH;
        }
        else if (inputCoordinates.getY() <= Y_LOW_BOUND)
        {
            yCoor = Y_LOW_BOUND + BORDER_WIDTH;
        }
        else
        {
            yCoor = inputCoordinates.getY();
        }

        return new Point2D(xCoor, yCoor);
    }

    public void updateStoredVariables(Map<String, List<Node>> variables) {

        TextArea varArea = mainAccess.getVarText();
        varArea.setText("");
        for(Map.Entry<String, List<Node>> varEntry : variables.entrySet())
        {
            varArea.setText(varArea.getText() + varEntry.getKey() + "\t\t\t" + varEntry.getValue().get(0).getTokenValue() + "\n");
        }
    }

    public void updateUserFunctions(Map<String, List<Node>> functions) {

        TextArea funcArea = mainAccess.getFuncText();
        funcArea.setText("");
        for(Map.Entry<String, List<Node>> funcEntry : functions.entrySet())
        {
            funcArea.setText(funcArea.getText() + funcEntry.getKey() + "\n");
        }
    }



}
