package Compiler.TokenParser.Nodes;

import Compiler.TokenParser.Token;
import Compiler.Turtle;
import Compiler.TurtleStatus;


public class IsShowingNode extends Node {
    Turtle turtle;
    public IsShowingNode(Token t, Turtle turtle){

        super(t);
        this.turtle = turtle;
    }


    public double evaluate(){
        int size = turtle.getHistory().size();
        TurtleStatus recentTurt = turtle.getHistory().get(size-1);

        boolean isShowing = recentTurt.getVisible();
        if(isShowing) {//turtle is visible
            return 1;
        }
        else{
            return 0;
        }
    }
}
