package Compiler.TokenParser.Nodes;

import Compiler.TokenParser.Token;
import Compiler.Turtle;
import Compiler.TurtleManager;
import Compiler.TurtleStatus;


import java.util.List;

public class IDNode extends Node{
    TurtleManager tManager;

    public IDNode(Token t,  TurtleManager tm){
        super(t);
        this.tManager = tm;

    }

    public double evaluate(){
        //if there are many active turtles, returns the last active turtle;
        List<Turtle> activeList = tManager.getActiveTurtles();
        int size = activeList.size();
        return activeList.get(size-1).getId();
    }
}
