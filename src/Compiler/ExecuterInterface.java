/**
 * Interface for a class that cal execute a list of parse trees made out of nodes.
 * @author mpz5
 */

package Compiler;

import Compiler.TokenParser.Nodes.Node;

import java.util.List;

public interface ExecuterInterface {

    /**
     * A method to execute all of the trees rooted at the nodes passed into the list in the parameter
     * @param heads list of tree roots.
     * @return a list of turtle status objects
     */
    List<Object> executeTrees(List<Node> heads);
}
