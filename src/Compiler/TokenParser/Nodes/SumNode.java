/**
 * @author mpz5
 */

package Compiler.TokenParser.Nodes;

import Compiler.TokenParser.Token;
import java.util.List;

public class SumNode extends Node {

    public SumNode(Token t){
        super(t);
    }


    public double evaluate(){
        List<Node> children = getChildren();
        double sum = (children.get(0).evaluate() + children.get(1).evaluate());
        return sum;
    }

}
