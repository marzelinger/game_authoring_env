/**
 * @author mpz5
 */

package Compiler.TokenParser.Nodes;

import Compiler.TokenParser.Token;
import Compiler.Turtle;

import java.util.function.Consumer;

public class RightNode extends ExecutableNode {
    public RightNode(Token t) {
        super(t);
    }

    public double evaluate() {
        return getChildren().get(0).evaluate();
    }


    public Consumer<Turtle> executeNode() {
        Consumer<Turtle> action = turtle -> turtle.turnRight(evaluate());
        return action;
    }

}
