package Compiler.TokenParser.Nodes;
import Compiler.TokenParser.Token;
import Compiler.Turtle;
import Compiler.TurtleStatus;

public class IsPenDownNode extends Node {
    Turtle turtle;

    public IsPenDownNode(Token t, Turtle turtle){
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

}
