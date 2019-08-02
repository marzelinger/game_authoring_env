package Compiler.TokenParser.Nodes;

import Compiler.TokenParser.Token;

import Compiler.Turtle;

import java.util.function.Consumer;

public class ShowTurtleNode extends ExecutableNode {

    Turtle turtle;

    public ShowTurtleNode(Token t, Turtle turtle){
        super(t);
        this.turtle = turtle;
    }

    public double evaluate(){
        //assuming that true means the pen is down
        return 1;
    }

    public Consumer<Turtle> executeNode(){
        Consumer<Turtle> action = turtle -> turtle.takeOffInvisibleCloak();
        return action;
    }
}
