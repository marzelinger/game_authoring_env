/**
 * @author mpz5
 */

package Compiler.TokenParser.Nodes;

import Compiler.Turtle;
import Compiler.TokenParser.Token;

import java.util.function.Consumer;

public class ForwardNode extends ExecutableNode {

    public ForwardNode(Token t){
        super(t);
    }

    public double evaluate(){
        return getChildren().get(0).evaluate();
    }

    public Consumer<Turtle> executeNode(){
        Consumer<Turtle> action = turtle -> turtle.moveForward(evaluate());
        return action;
    }

}
