package Compiler.TokenParser.Nodes;

import Compiler.TokenParser.Token;

import java.util.List;
import Compiler.TurtleManager;
import Compiler.TurtleStatus;

public class TurtlesNode extends Node {
    TurtleManager tManager;

    public TurtlesNode(Token t, TurtleManager tm){
        super(t);
        this.tManager = tm;

    }

    public double evaluate(){
        //returns number of turtles created so far
        return tManager.getAllTurtles().size();
    }
}
