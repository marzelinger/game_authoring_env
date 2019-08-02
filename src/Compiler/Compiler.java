/**
 * External API for the back end of SLogo. Given interactions for the compiler and the controller.
 */

package Compiler;

import java.util.List;

public interface Compiler {

    /**
     * This method compiles the code into a list of lists of Turtle Status objects.
     * @param code string to be compiled
     * @param activeTurtles list of currently active turtles
     * @param language language that the string is in
     * @return
     */
    List<List<Object>> compile(String code, List<Turtle> activeTurtles, String language);

    /**
     * This method saves all current variables and functions to a file named fileName.slogo
     * @param fileName
     */
    void save(String fileName);

    /**
     * this function loads all variabels and functions from fileName.
     * @param fileName
     * @return
     */
    String loadFile(String fileName);

}
