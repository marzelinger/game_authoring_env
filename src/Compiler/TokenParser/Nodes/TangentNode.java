/**
 * @author mpz5
 */

package Compiler.TokenParser.Nodes;

import Compiler.TokenParser.Token;
import java.util.List;

public class TangentNode extends Node {

    public TangentNode(Token t){
        super(t);
    }

    public double evaluate(){
        List<Node> children = getChildren();
        return Math.tan(Double.parseDouble(children.get(0).getTokenValue()));
    }
}