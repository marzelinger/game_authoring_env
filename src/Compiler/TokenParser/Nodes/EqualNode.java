/**
 * @author mpz5
 */
package Compiler.TokenParser.Nodes;

import Compiler.SyntaxParser;
import Compiler.TokenParser.Token;



import java.util.List;

public class EqualNode extends Node {

    public EqualNode(Token t){
        super(t);
    }

    public double evaluate(){
        List<Node> children = getChildren();
        if(children.size() != 2){
            throw new Error(SyntaxParser.SYNTAX_ERROR);
        }
        if(children.get(0).evaluate() == children.get(1).evaluate()){
            return 1;
        };
        return 0;
    }

}
