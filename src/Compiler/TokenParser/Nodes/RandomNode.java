/**
 * @author mpz5
 */

package Compiler.TokenParser.Nodes;

import Compiler.TokenParser.Token;
import java.util.List;

public class RandomNode extends Node {

    public RandomNode(Token t){
        super(t);
    }

    public double evaluate(){
        List<Node> children = getChildren();
        return Math.random() * children.get(0).evaluate();
    }

}
