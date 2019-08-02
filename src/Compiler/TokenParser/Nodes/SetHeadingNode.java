package Compiler.TokenParser.Nodes;

import Compiler.TokenParser.Token;

import Compiler.Turtle;

import java.util.function.Consumer;

public class SetHeadingNode extends ExecutableNode {

    Turtle turtle;
    public SetHeadingNode(Token t){
        super(t);
        this.turtle = turtle;
    }


    public double evaluate(){

        return getChildren().get(0).evaluate();
    }
    public Consumer<Turtle> executeNode(){
        Consumer<Turtle> action = turtle -> turtle.setHeading(evaluate());
        return action;
    }
}
