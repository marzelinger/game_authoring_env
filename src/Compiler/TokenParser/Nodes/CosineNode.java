/**
 * @author mpz5
 */

package Compiler.TokenParser.Nodes;

import Compiler.TokenParser.Token;
import Compiler.SyntaxParser;
import java.util.List;

public class CosineNode extends Node {

    public CosineNode(Token t){
        super(t);
    }


    public double evaluate(){
        List<Node> children = getChildren();
        if(children.size() > 1){
            throw new Error(SyntaxParser.SYNTAX_ERROR);
        }
        return Math.cos(Double.parseDouble(children.get(0).getTokenValue()));
    }
}
