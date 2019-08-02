package Compiler.TokenParser.Nodes;
import Compiler.TokenParser.Token;
import Compiler.Turtle;

import java.awt.geom.Point2D;
import java.util.function.Consumer;
import Compiler.TurtleStatus;

public class PenDownNode extends ExecutableNode {
    Turtle turtle;

    public PenDownNode(Token t, Turtle turtle){
        super(t);
        this.turtle = turtle;
    }


    public double evaluate(){
        int size = turtle.getHistory().size();
        TurtleStatus recentTurt = turtle.getHistory().get(size-1);

        boolean status = recentTurt.getPenStatus();
        if(status) {//pen is already down
            return 1;
        }
        else{
            return 0;
        }
    }

    public Consumer<Turtle> executeNode(){

        Consumer<Turtle> action = turtle ->  turtle.putPenDown();

        return action;
    }
}
