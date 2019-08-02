package Compiler.TokenParser.Nodes;

import Compiler.TokenParser.Token;
import Compiler.TurtleStatus;
import Compiler.Turtle;

import javafx.geometry.Point2D;
import java.util.List;

public class XCoordinateNode extends Node {
    Turtle turtle;
    public XCoordinateNode(Token t, Turtle turtle){

        super(t);
        this.turtle = turtle;
    }


    public double evaluate(){
        int size = turtle.getHistory().size();
        TurtleStatus recentTurt = turtle.getHistory().get(size-1);
        Point2D Coords= recentTurt.getLocation();
        double X = ((Point2D) Coords).getX();
        return X;
    }
}
