/**
 * This abstract class extends the Node class and is extended by the abstract StructuredNode class as well as other executable nodes such as ForwardNode class. All subclasses of this node implement an executeNode function, which is used to return a Consumer of a Turtle object. These functions are called in the Executor class where each node in the tree is executed.
 * @author mpz5
 */
package Compiler.TokenParser.Nodes;

import Compiler.TokenParser.Token;
import Compiler.Turtle;

import java.util.function.Consumer;

public abstract class ExecutableNode extends Node {
    private boolean executed;

    public ExecutableNode(Token t){
        super(t);
        executed = false;
    }

    /**
     * Setter for executed instance variable. This is used in the AskWithNode.
     */
    protected void setExecuted(){
        executed = true;
    }

    /**
     * This function is used by several types of instructions in which a node must be able to be executed more than once. For example, loops. In loops, one full loop is completed and then all executable nodes are reset such that they can be executed again.
     */
    protected void resetExecuted(){ executed = false; }

    /**
     * This function is implemented by all subclasses and returns a Turtle Consumer that is called in the Executor class in order to execute all operations on the correct turtles.
     * @return Turtle Consumer
     */
    public abstract Consumer<Turtle> executeNode();

    public Consumer<Turtle> execute(){
        if(!executed){
            executed = true;
            return executeNode();

        }
        return turtle -> {};

    }

}
