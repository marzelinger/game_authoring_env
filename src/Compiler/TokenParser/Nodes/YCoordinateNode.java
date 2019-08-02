package Compiler.TokenParser.Nodes;

import Compiler.TokenParser.Token;
import Compiler.Turtle;
import Compiler.TurtleStatus;

import javafx.geometry.Point2D;

public class YCoordinateNode extends Node {
    Turtle turtle;
    public YCoordinateNode(Token t, Turtle turtle){

        super(t);
        this.turtle = turtle;
    }


    public double evaluate(){
        int size = turtle.getHistory().size();
        TurtleStatus recentTurt = turtle.getHistory().get(size-1);
        Point2D Coords= recentTurt.getLocation();
        double Y = ((Point2D) Coords).getY();
        return Y;
    }
}
