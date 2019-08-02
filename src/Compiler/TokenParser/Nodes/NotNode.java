/**
 * @author mpz5
 */

package Compiler.TokenParser.Nodes;

import Compiler.TokenParser.Token;
import java.util.List;

public class NotNode extends Node {

    public NotNode(Token t){
        super(t);
    }

    public double evaluate(){
        List<Node> children = getChildren();
        if(children.get(0).evaluate() == 0){

            return 1;
        };
        return 0;
    }

}
