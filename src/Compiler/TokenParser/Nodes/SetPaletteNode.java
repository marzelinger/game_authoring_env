package Compiler.TokenParser.Nodes;

import Compiler.TokenParser.Token;

import java.util.function.Consumer;
import Compiler.Turtle;

public class SetPaletteNode extends ExecutableNode {
    //I'm confused, this sets the palette for what?

    Turtle turtle;

    public SetPaletteNode(Token t, Turtle turtle){
        super(t);
        this.turtle = turtle;
    }

    public double evaluate(){
        //returns the index

        return getChildren().get(0).evaluate();
    }

    //FIXME: go back to commands Map to decide the structure of the tree
    public Consumer<Turtle> executeNode(){
        Consumer<Turtle> action = turtle -> {
            turtle.setPalette(getChildren().get(0).evaluate(), getChildren().get(1).evaluate(), getChildren().get(2).evaluate(),  getChildren().get(3).evaluate()) ;
        };
        return action;
    }
}
