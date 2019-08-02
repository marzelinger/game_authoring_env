/**
 * @author mpz5
 */

package Compiler.TokenParser.Nodes;

import Compiler.TokenParser.Token;
import java.util.List;

public class NotEqualNode extends Node {

    public NotEqualNode(Token t){
        super(t);
    }


    public double evaluate(){
        List<Node> children = getChildren();
        if(children.get(0).evaluate() != children.get(1).evaluate()){
            return 1;
        };
        return 0;
    }

}
