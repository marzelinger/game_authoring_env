/**
 * @author mpz5
 */

package Compiler.TokenParser.Nodes;

import Compiler.SyntaxParser;
import Compiler.TokenParser.Token;

import java.util.List;

public class ArcTangentNode extends Node {

    public ArcTangentNode(Token t){
        super(t);
    }


    public double evaluate(){
        List<Node> children = getChildren();
        if(children.size() > 1){
            throw new Error(SyntaxParser.SYNTAX_ERROR);
        }
        return Math.atan(Double.parseDouble(children.get(0).getTokenValue()));
    }
}