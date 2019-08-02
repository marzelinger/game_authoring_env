/**
 * This class is responsible for executing a list of parse trees made up of nodes on the current turtle. This is achieved by iterating through all the nodes in all of the trees and executing all of the nodes that are executable.
 *
 * @author mpz5
 */

package Compiler;

import Compiler.TokenParser.Nodes.*;
import Compiler.TokenParser.TokenType;

import java.util.ArrayList;
import java.util.List;

public class Executer implements ExecuterInterface{
    Turtle turtle;

    public Executer(Turtle currentTurtle) {
        turtle = currentTurtle;
    }

    /**
     * This function takes a list of Nodes that are roots of parse trees and executed each of them on the current turtle. A combination of the evaluate and execute methods are used to convert these parse trees into actions on turtles. These actions update the history of all of these turtles, which are then passed to the front end as a list of turtle statuses.
     * @param heads
     * @return
     */
    public List<Object> executeTrees(List<Node> heads) {
        List<Object> returnVals = new ArrayList<Object>();
        for (Node head : heads) {
            executeTree(head);
        }
        List<TurtleStatus> tsList = turtle.getHistory();
        returnVals.add(tsList);
        List<Double> vals = new ArrayList<Double>();
        for (Node n : heads) {
            vals.add(n.evaluate());
        }
        returnVals.add(vals);
        return returnVals;
    }

    /**
     * This is a helper method used to execute one of the trees passed to the executeTrees function.
     * @param head
     */
    private void executeTree(Node head) {
        executeNode(head);
        if(head.checkType(TokenType.MakeUserInstruction) || head.checkType(TokenType.Define) || head.checkType(TokenType.IfElse)){
            return;
        }
        for (Node child : head.getChildren()) {
                executeTree(child);
        }
    }

    /**
     * This is a helper method used to execute one node within a tree
     * @param n
     */
    private void executeNode(Node n){
        if(n.checkExecutable()){
            ((ExecutableNode)n).execute().accept(turtle);
        }
    }

}
