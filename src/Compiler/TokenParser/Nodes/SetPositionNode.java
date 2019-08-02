package Compiler.TokenParser.Nodes;
import Compiler.TurtleStatus;
import Compiler.TokenParser.Token;
import Compiler.Turtle;

import javafx.geometry.Point2D;
import java.util.List;
import java.util.function.Consumer;

public class SetPositionNode extends ExecutableNode {
    Turtle turtle;

    public SetPositionNode(Token t, Turtle turtle){
        super(t);
        this.turtle = turtle;
    }

    public double evaluate(){
        int size = turtle.getHistory().size();
        TurtleStatus recentTurt = turtle.getHistory().get(size-1);
        Point2D oldCoords= recentTurt.getLocation();
        double oldX = ((Point2D) oldCoords).getX();
        double oldY = ((Point2D) oldCoords).getY();

        //assumes that the first child of the Node is the x argument, second child is the y
        return Math.sqrt( Math.pow(oldX - getChildren().get(0).evaluate(), 2) + Math.pow(oldY - getChildren().get(1).evaluate(), 2));

    }

    public Consumer<Turtle> executeNode(){
        Consumer<Turtle> action = turtle -> turtle.goTo(getChildren().get(0).evaluate(), getChildren().get(1).evaluate());
        return action;
    }
}
