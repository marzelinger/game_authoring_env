/**
 * @author mpz5
 */

package Compiler.TokenParser.Nodes;

import Compiler.SyntaxParser;
import Compiler.TokenParser.Token;

import java.util.List;

public class DifferenceNode extends Node {

    public DifferenceNode(Token t){
        super(t);
    }
    public double evaluate(){
        List<Node> children = getChildren();
        if(children.size() > 2){
            throw new Error(SyntaxParser.SYNTAX_ERROR);
        }
        return children.get(0).evaluate() - children.get(1).evaluate();
    }

}
