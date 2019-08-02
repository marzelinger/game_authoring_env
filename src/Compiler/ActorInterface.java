package Compiler;

import java.util.List;
import java.util.Map;

public interface ActorInterface {

    TurtleStatus moveForward(double distance);

    TurtleStatus moveBack(double distance);

    TurtleStatus turnRight(double degrees);

    TurtleStatus turnLeft(double degrees);

    TurtleStatus setHeading(double degrees);

    TurtleStatus turnTowards(double x, double y);

    TurtleStatus goTo(double x, double y);

    TurtleStatus putPenDown();

    TurtleStatus putPenUp();

    TurtleStatus takeOffInvisibleCloak();

    TurtleStatus wearInvisibleCloak();

    TurtleStatus goHome();

    TurtleStatus clearScreen();

    //Extension additional methods

    TurtleStatus setBackground(double index);

    TurtleStatus setPenColor(double index);

    TurtleStatus setPenSize(double index);

    TurtleStatus setShape(double index);

    TurtleStatus setPalette(double index, double r, double g, double b);

    List<TurtleStatus> getHistory();

}