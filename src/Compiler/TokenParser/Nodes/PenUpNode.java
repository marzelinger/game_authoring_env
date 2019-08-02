package Compiler.TokenParser.Nodes;

import Compiler.TokenParser.Token;
import Compiler.Turtle;

import java.util.function.Consumer;
import Compiler.TurtleStatus;

public class PenUpNode extends ExecutableNode {
    Turtle turtle;

    public PenUpNode(Token t, Turtle turtle){//String query
        super(t);
        this.turtle = turtle;
//        if(query.equals("true")){
//            //this means that it won't execute when we iterate through the tree
//            setExecuted();
//        }
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
        Consumer<Turtle> action = turtle -> turtle.putPenUp();
        return action;
    }
}
