package Compiler.TokenParser.Nodes;

import Compiler.TokenParser.Token;
import Compiler.Turtle;
import Compiler.TurtleStatus;

import java.util.function.Consumer;

public class HeadingNode extends ExecutableNode {
    Turtle turtle;
    public HeadingNode(Token t, Turtle turtle){

        super(t);
        this.turtle = turtle;
    }


    public double evaluate(){
        int size = turtle.getHistory().size();
        TurtleStatus recentTurtle = turtle.getHistory().get(size-1);
        return recentTurtle.getOrientation();
    }

    @Override
    public Consumer<Turtle> executeNode() {
        Consumer<Turtle> action = turt -> {
            turt.setHeading(evaluate());
        };
        return action;
    }
}
