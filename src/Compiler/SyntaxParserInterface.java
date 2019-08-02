/**
 * An interface for a class that can build a parse tree given a list of input tokens and a turtle.
 */

package Compiler;

import Compiler.TokenParser.Nodes.Node;
import Compiler.TokenParser.Token;
import java.util.List;

public interface SyntaxParserInterface {

    /**
     * A function to return a list of root nodes of trees build from the list of input tokens.
     * @param inputTokens list of tokens used to create nodes
     * @param currTurtle current turtle
     * @return
     */
    List<Node> buildTrees(List<Token> inputTokens, Turtle currTurtle);
}
