package Compiler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created for the extension. Passed along with TurtleStatus; tells about pen and background
 */

public class VisualStatus {
    private boolean penStatus;
    private double backGround;
    private double penColor;
    private double penSize;
    private double turtleShape;
    private double palette;

    private HashMap<Double, List<Double>> colorPalette;

    public VisualStatus(boolean penStatus, double backGround, double penColor, double penSize, double turtleShape, double palette){
        this.penStatus = penStatus;
        this.backGround = backGround;
        this.penColor = penColor;
        this.penSize = penSize;
        this.turtleShape = turtleShape;
        this.palette = palette;
    }

    public boolean getPenStatus(){
        return penStatus;
    }

    public double getBackGround(){
        return backGround;
    }

    public double getPenColor(){
        return penColor;
    }

    public double getPenSize(){
        return penSize;
    }
    public HashMap<Double, List<Double>> getColorPalette(){
        return colorPalette;
    }

    public double getTurtleShape(){
        return turtleShape;
    }

    public double getPalette(){
        return palette;
    }

    public Map<String, Double> convertToMap(){
        int intPS = getPenStatus() ? 1 : 0;
        double penStatus= (double) intPS;

        Map<String, Double> tsMap = Map.ofEntries(
                Map.entry("PenStatus", penStatus),
                Map.entry("BackGround", getBackGround()),
                Map.entry("PenColor", getPenColor()),
                Map.entry("PenSize", getPenSize()),
                Map.entry("TurtleShape", getTurtleShape()),
                Map.entry("Palette", getPalette())
        );

        return tsMap;
    }

}
