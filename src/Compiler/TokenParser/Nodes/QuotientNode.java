/**
 * @author mpz5
 */

package Compiler.TokenParser.Nodes;

import Compiler.TokenParser.Token;
import java.util.List;

public class QuotientNode extends Node {

    public QuotientNode(Token t){
        super(t);
    }


    public double evaluate(){
        List<Node> children = getChildren();
        return children.get(0).evaluate() / children.get(1).evaluate();
    }

}
