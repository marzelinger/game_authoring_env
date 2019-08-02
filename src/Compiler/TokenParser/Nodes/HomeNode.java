/**
 * @author mpz5
 */

package Compiler.TokenParser.Nodes;

import Compiler.TokenParser.Token;
import Compiler.Turtle;

import java.util.function.Consumer;

public class HomeNode extends ExecutableNode {
    Turtle turtle;

    public HomeNode(Token t, Turtle turtle){
        super(t);
        this.turtle = turtle;
    }

    public double evaluate(){
        return 1;
    }

    public Consumer<Turtle> executeNode(){
        Consumer<Turtle> action = turtle -> turtle.goHome();
        return action;
    }
}
