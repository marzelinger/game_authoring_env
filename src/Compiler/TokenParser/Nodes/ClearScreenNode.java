package Compiler.TokenParser.Nodes;

import Compiler.TokenParser.Token;
import Compiler.Turtle;

import java.util.function.Consumer;

public class ClearScreenNode extends ExecutableNode {
    Turtle turtle;

    public ClearScreenNode(Token t, Turtle turtle){
        super(t);
        this.turtle = turtle;
    }

    public double evaluate(){
        return 1;
    }

    public Consumer<Turtle> executeNode(){
        Consumer<Turtle> action = turtle -> turtle.clearScreen();
        return action;
    }
}
