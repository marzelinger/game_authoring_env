/**
 * @author mpz5
 */

package Compiler.TokenParser.Nodes;

import Compiler.TokenParser.Token;
import Compiler.Turtle;
import Compiler.TurtleStatus;
import java.util.function.Consumer;

public class HideTurtleNode extends ExecutableNode {
    Turtle turtle;

    public HideTurtleNode(Token t, Turtle turtle){
        super(t);
        this.turtle = turtle;
    }

    public double evaluate(){
        int size = turtle.getHistory().size();
        TurtleStatus recentTurtle = turtle.getHistory().get(size-1);
        if(recentTurtle.getPenStatus()){
            return 1;
        };
        //assuming that true means the pen is down
        return 0;
    }

    public Consumer<Turtle> executeNode(){
        Consumer<Turtle> action = turtle -> turtle.wearInvisibleCloak();
        return action;
    }
}
