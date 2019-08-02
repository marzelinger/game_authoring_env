package Compiler;

import javafx.geometry.Point2D;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TurtleStatus {

    private boolean penStatus; //True means the pen is down
    private double orientation;
    private double XPos;
    private double YPos;
    private boolean visible; //True means that you can see turtle
    private boolean clearScreen;
    private double ID;
    private VisualStatus visualStatus;
    private boolean active;


    private Point2D loc;


    public TurtleStatus(double x, double y, double deg, boolean penStat, boolean sheThere, boolean clear, double id, VisualStatus vs, boolean active) {
        XPos = x;
        YPos = y;
        loc = new Point2D(x,y);
        orientation = deg;
        penStatus = penStat;
        visible = sheThere;
        clearScreen = clear;
        ID = id;
        visualStatus = vs;
        this.active = active;
    }

    public boolean getActive(){
        return active;
    }
    public double getOrientation() {
        return orientation;
    }
    public double getID() {
        return ID;
    }

    public VisualStatus getVisualStatus() {
        return visualStatus;
    }


    public Point2D getLocation() {
        Point2D loc = new Point2D(XPos, YPos);
        return loc;
    }

    public void setLocation(Point2D newPoint) {
        this.loc = newPoint;
        this.XPos = newPoint.getX();
        this.YPos = newPoint.getY();
    }


    public boolean getPenStatus() {
        return penStatus;
    }


    public boolean getVisible() {
        return visible;
    }

    public boolean getClearScreen() {

        return clearScreen;
    }


    public String toString() {
        return "[ PenStatus: " + penStatus + ", YPosition: " + YPos + ", XPosition: " + XPos + ", Orientation: " + orientation + ", Visible: " + visible + ", ClearScreen: " + clearScreen +  ", Active: " + active + "] ";
    }

    //this will return List<Object>
    public Map<String, Double> convertToMap() {

        ArrayList<Object> result = new ArrayList<Object>();
        double penStatusDouble = penStatus ? 1.0 : 0.0;
        double visibleDouble = visible ? 1.0 : 0.0;
        double clearScreenDouble = clearScreen ? 1.0 : 0.0;
        double activeDouble = active ? 1.0 : 0.0;

        Map<String, Double> tsMap = Map.ofEntries(
                Map.entry("XPosition", getLocation().getX()),
                Map.entry("YPosition", getLocation().getY()),
                Map.entry("Orientation", getOrientation()),
                Map.entry("PenStatus", penStatusDouble),
                Map.entry("Visible", visibleDouble),
                Map.entry("ClearScreen", clearScreenDouble),
                Map.entry("Active", activeDouble),
                Map.entry("ID", ID)
        );

        result.add(tsMap);

        Map<String, Double> vsMap = getVisualStatus().convertToMap();

        result.add(vsMap);

        return tsMap;
    }

}
