/**
 * This class is an interface for a node factory that can be used to create Nodes.
 * @author mpz5
 */

package Compiler.TokenParser;

import Compiler.TokenParser.Nodes.Node;
import Compiler.Turtle;

public interface NodeFactoryInterface {

    /**
     * creates a node given a particular token and turtle.
     * @param t - token to create node from
     * @param currTurtle - current turtle
     * @return Node
     * @throws Exception
     */
    Node createNode(Token t, Turtle currTurtle) throws Exception;
}
