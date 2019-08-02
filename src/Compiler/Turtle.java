package Compiler;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.*;

public class Turtle implements ActorInterface {
    private List<TurtleStatus> history;
    private boolean active;
    private int id;

    public Turtle(int id) {
        this.id = id;
        active = true;
        history = new ArrayList<TurtleStatus>();
        VisualStatus defaultVisuals = new VisualStatus(true, 0, 0, 10, 0, 0);
        history.add(new TurtleStatus(0, 0, 90, true, true, false, id, defaultVisuals, true));

    }

    public boolean checkActive(){
        return active;
    };

    public int getId(){
        return id;
    }

    public TurtleStatus activateTurtle(){
        TurtleStatus oldTurtle = history.get(history.size() - 1);
        TurtleStatus result = new TurtleStatus(oldTurtle.getLocation().getX(), oldTurtle.getLocation().getY(), oldTurtle.getOrientation(), oldTurtle.getPenStatus(), oldTurtle.getVisible(), oldTurtle.getClearScreen(), oldTurtle.getID(), oldTurtle.getVisualStatus(), true);
        history.add(result);
        return result;
    }

    public TurtleStatus deactivateTurtle(){
        TurtleStatus oldTurtle = history.get(history.size() - 1);
        TurtleStatus result = new TurtleStatus(oldTurtle.getLocation().getX(), oldTurtle.getLocation().getY(), oldTurtle.getOrientation(), oldTurtle.getPenStatus(), oldTurtle.getVisible(), oldTurtle.getClearScreen(), oldTurtle.getID(), oldTurtle.getVisualStatus(), false);
        history.add(result);
        return result;
    }

    public TurtleStatus moveForward(double pixels) {
        System.out.println("moving forward");
        TurtleStatus oldTurtle = history.get(history.size() - 1);
        System.out.println(oldTurtle.toString());
        double oldX = oldTurtle.getLocation().getX();
        double oldY = oldTurtle.getLocation().getY();
        double oldOrientation = oldTurtle.getOrientation();

        double newX = oldX + Math.cos(Math.toRadians(oldOrientation)) * pixels;
        double newY = oldY - Math.sin(Math.toRadians(oldOrientation)) * pixels;


        TurtleStatus result = new TurtleStatus(newX, newY, oldOrientation, oldTurtle.getPenStatus(), oldTurtle.getVisible(), false, id, oldTurtle.getVisualStatus(), oldTurtle.getActive());

        history.add(result);
        return result;

    }

    public TurtleStatus moveBack(double pixels) {
        TurtleStatus oldTurtle = history.get(history.size() - 1);
        double oldX = oldTurtle.getLocation().getX();
        double oldY = oldTurtle.getLocation().getY();
        double oldOrientation = oldTurtle.getOrientation();

        double newX = oldX - Math.cos(Math.toRadians(oldOrientation)) * pixels;
        double newY = oldY + Math.sin(Math.toRadians(oldOrientation)) * pixels;

        TurtleStatus result = new TurtleStatus(newX, newY, oldOrientation, oldTurtle.getPenStatus(), oldTurtle.getVisible(), false, id, oldTurtle.getVisualStatus(), oldTurtle.getActive());
        history.add(result);
        return result;

    }

    public TurtleStatus turnLeft(double degrees) {

        TurtleStatus oldTurtle = history.get(history.size() - 1);
        double oldX = oldTurtle.getLocation().getX();
        double oldY = oldTurtle.getLocation().getY();
        double oldOrientation = oldTurtle.getOrientation();

        double newOrientation = oldOrientation + degrees;

        newOrientation = (Math.abs(newOrientation) > 360) ? newOrientation % 360 : newOrientation;

        TurtleStatus result = new TurtleStatus(oldX, oldY, newOrientation, oldTurtle.getPenStatus(), oldTurtle.getVisible(), false, id, oldTurtle.getVisualStatus(), oldTurtle.getActive());
        history.add(result);
        return result;
    }

    public TurtleStatus turnRight(double degrees) {
        TurtleStatus oldTurtle = history.get(history.size() - 1);
        double oldX = oldTurtle.getLocation().getX();
        double oldY = oldTurtle.getLocation().getY();
        double oldOrientation = oldTurtle.getOrientation();

        double newOrientation = oldOrientation - degrees;
        newOrientation = (Math.abs(newOrientation) < 0) ? newOrientation : 360 + newOrientation;
        newOrientation = (Math.abs(newOrientation) < 0) ? newOrientation : 360 + newOrientation;


        TurtleStatus result = new TurtleStatus(oldX, oldY, newOrientation, oldTurtle.getPenStatus(), oldTurtle.getVisible(), false, id, oldTurtle.getVisualStatus(), oldTurtle.getActive());
        history.add(result);
        return result;
    }

    public TurtleStatus setHeading(double degrees) {
        TurtleStatus oldTurtle = history.get(history.size() - 1);
        double oldX = oldTurtle.getLocation().getX();
        double oldY = oldTurtle.getLocation().getY();

        TurtleStatus result = new TurtleStatus(oldX, oldY, degrees, oldTurtle.getPenStatus(), oldTurtle.getVisible(), false, id, oldTurtle.getVisualStatus(), oldTurtle.getActive());
        history.add(result);
        return result;
    }

    public TurtleStatus turnTowards(double x, double y) {
        TurtleStatus oldTurtle = history.get(history.size() - 1);
        double oldX = oldTurtle.getLocation().getX();
        double oldY = oldTurtle.getLocation().getY();

        double xDiff = x - oldX;
        double yDiff = y - oldY;
        double degrees = Math.tan(yDiff / xDiff) + (360 - oldTurtle.getOrientation());

        TurtleStatus result = new TurtleStatus(oldX, oldY, degrees, oldTurtle.getPenStatus(), oldTurtle.getVisible(), false, id, oldTurtle.getVisualStatus(), oldTurtle.getActive());
        history.add(result);
        return result;
    }

    public TurtleStatus goTo(double x, double y) {
        TurtleStatus oldTurtle = history.get(history.size() - 1);
        TurtleStatus result = new TurtleStatus(x, y, oldTurtle.getOrientation(), oldTurtle.getPenStatus(), oldTurtle.getVisible(), false, id, oldTurtle.getVisualStatus(), oldTurtle.getActive());
        history.add(result);
        return result;
    }

    public TurtleStatus putPenDown() {
        TurtleStatus oldTurtle = history.get(history.size() - 1);
        double oldX = oldTurtle.getLocation().getX();
        double oldY = oldTurtle.getLocation().getY();

        if (oldTurtle.getPenStatus()) { //means that the pen is already down
            history.add(oldTurtle);
            return oldTurtle;
        } else {
            VisualStatus vs = oldTurtle.getVisualStatus();
            VisualStatus newVS = new VisualStatus(true, vs.getBackGround(), vs.getPenColor(), vs.getPenSize(), vs.getTurtleShape(), vs.getPalette());

            TurtleStatus result = new TurtleStatus(oldX, oldY, oldTurtle.getOrientation(), !oldTurtle.getPenStatus(), oldTurtle.getVisible(), false, id, newVS, oldTurtle.getActive());
            history.add(result);
            return result;
        }

    }


    public TurtleStatus putPenUp() {
        TurtleStatus oldTurtle = history.get(history.size() - 1);
        double oldX = oldTurtle.getLocation().getX();
        double oldY = oldTurtle.getLocation().getY();

        if (!oldTurtle.getPenStatus()) { //means that the pen is already up
            history.add(oldTurtle);
            return oldTurtle;
        } else {
            VisualStatus vs = oldTurtle.getVisualStatus();
            VisualStatus newVS = new VisualStatus(false, vs.getBackGround(), vs.getPenColor(), vs.getPenSize(), vs.getTurtleShape(), vs.getPalette());

            TurtleStatus result = new TurtleStatus(oldX, oldY, oldTurtle.getOrientation(), !oldTurtle.getPenStatus(), oldTurtle.getVisible(), false, id, newVS, oldTurtle.getActive());
            history.add(result);
            return result;

        }

    }

    public TurtleStatus takeOffInvisibleCloak() {
        TurtleStatus oldTurtle = history.get(history.size() - 1);
        double oldX = oldTurtle.getLocation().getX();
        double oldY = oldTurtle.getLocation().getY();
        TurtleStatus result = new TurtleStatus(oldX, oldY, oldTurtle.getOrientation(), oldTurtle.getPenStatus(), true, false, id, oldTurtle.getVisualStatus(), oldTurtle.getActive());
        history.add(result);
        return result;
    }

    public TurtleStatus wearInvisibleCloak() {
        TurtleStatus oldTurtle = history.get(history.size() - 1);
        double oldX = oldTurtle.getLocation().getX();
        double oldY = oldTurtle.getLocation().getY();

        if (!oldTurtle.getVisible()) {//not visible
            history.add(oldTurtle);
            return oldTurtle;
        } else {
            TurtleStatus result = new TurtleStatus(oldX, oldY, oldTurtle.getOrientation(), oldTurtle.getPenStatus(), !oldTurtle.getVisible(), false, id, oldTurtle.getVisualStatus(), oldTurtle.getActive());
            history.add(result);
            return result;
        }
    }

    public TurtleStatus goHome() {
        TurtleStatus oldTurtle = history.get(history.size() - 1);
        TurtleStatus result = new TurtleStatus(0, 0, oldTurtle.getOrientation(), oldTurtle.getPenStatus(), oldTurtle.getVisible(), false, id, oldTurtle.getVisualStatus(), oldTurtle.getActive());
        history.add(result);
        return result;
    }

    public TurtleStatus clearScreen() {
        //Need to tell the frontend to clear everything

        TurtleStatus oldTurtle = history.get(history.size() - 1);
        TurtleStatus result = new TurtleStatus(0, 0, oldTurtle.getOrientation(), oldTurtle.getPenStatus(), oldTurtle.getVisible(), true, id, oldTurtle.getVisualStatus(), oldTurtle.getActive());
        history.add(result);
        return result;

    }


    public TurtleStatus setBackground(double index){
        TurtleStatus oldTurtle = history.get(history.size() - 1);
        VisualStatus vs = oldTurtle.getVisualStatus();
        VisualStatus newVS = new VisualStatus(vs.getPenStatus(), index, vs.getPenColor(), vs.getPenSize(), vs.getTurtleShape(), vs.getPalette());
        TurtleStatus result = new TurtleStatus(0, 0, oldTurtle.getOrientation(), oldTurtle.getPenStatus(), oldTurtle.getVisible(), true, id, newVS, oldTurtle.getActive());
        history.add(result);
        return result;
    }

    public TurtleStatus setPenColor(double index){

        TurtleStatus oldTurtle = history.get(history.size() - 1);
        VisualStatus vs = oldTurtle.getVisualStatus();
        VisualStatus newVS = new VisualStatus(vs.getPenStatus(), vs.getBackGround(), index, vs.getPenSize(), vs.getTurtleShape(), vs.getPalette());
        TurtleStatus result = new TurtleStatus(0, 0, oldTurtle.getOrientation(), oldTurtle.getPenStatus(), oldTurtle.getVisible(), true, id, newVS, oldTurtle.getActive());
        history.add(result);
        return result;

    }

    public TurtleStatus setPenSize(double index){
        if(index < 0){
            throw new Error("Invalid pen size");
        }
        else{
            TurtleStatus oldTurtle = history.get(history.size() - 1);
            VisualStatus vs = oldTurtle.getVisualStatus();
            VisualStatus newVS = new VisualStatus(vs.getPenStatus(), vs.getBackGround(), vs.getPenColor(), index, vs.getTurtleShape(), vs.getPalette());
            TurtleStatus result = new TurtleStatus(0, 0, oldTurtle.getOrientation(), oldTurtle.getPenStatus(), oldTurtle.getVisible(), true, id, newVS, oldTurtle.getActive());
            history.add(result);
            return result;

        }


    }

    public TurtleStatus setShape(double index){
        TurtleStatus oldTurtle = history.get(history.size() - 1);
        VisualStatus vs = oldTurtle.getVisualStatus();
        VisualStatus newVS = new VisualStatus(vs.getPenStatus(), vs.getBackGround(), vs.getPenColor(), vs.getPenSize(), index, vs.getPalette());
        TurtleStatus result = new TurtleStatus(0, 0, oldTurtle.getOrientation(), oldTurtle.getPenStatus(), oldTurtle.getVisible(), true, id, newVS, oldTurtle.getActive());
        history.add(result);
        return result;


    }

    //TODO: what this do tho

    public TurtleStatus setPalette(double index, double r, double g, double b){
        TurtleStatus oldTurtle = history.get(history.size() - 1);
        if(checkColors(r) && checkColors(g) &&checkColors(b)){

            VisualStatus vs = oldTurtle.getVisualStatus();
            ArrayList<Double> colors =  new ArrayList<Double>();
            colors.add(r);
            colors.add(g);
            colors.add(b);

            vs.getColorPalette().put(index, colors);


        }
        return oldTurtle;
    }

    public List<TurtleStatus> getHistory() {
        return history;
    }

    public boolean checkColors(double c){
        if((c >= 0) && (c < 256)){
            return true;
        }
        else{
            return false;
        }
    }


}
