package Compiler.TokenParser.Nodes;

import Compiler.TokenParser.Token;
import Compiler.Turtle;
import Compiler.TurtleStatus;

import java.util.function.Consumer;

public class SetTowardsNode extends ExecutableNode {

    Turtle turtle;

    public SetTowardsNode(Token t, Turtle turtle){
        super(t);
        this.turtle = turtle;
    }

    /**
     * The TOWARDS x y command in SLogo returns the number of degrees the turtle has turned.
     * TODO: evaluate method to return number of degrees returned

     */
    public double evaluate(){
        //returns the number of degrees turned
        int size = turtle.getHistory().size();
        TurtleStatus recentTurtle = turtle.getHistory().get(size-1);
        double oldDegrees = recentTurtle.getOrientation();

        //assumes that X will be found in the first child y in the second
        double oldX = getChildren().get(0).evaluate();
        double oldY = getChildren().get(1).evaluate();

        //formula for the number of degrees turned:

        return (Math.atan2(oldY, oldX) - oldDegrees);
    }

    public Consumer<Turtle> executeNode(){
        Consumer<Turtle> action = turtle -> turtle.turnTowards(getChildren().get(0).evaluate(), getChildren().get(1).evaluate());
        return action;
    }
}
