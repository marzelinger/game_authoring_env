/**
 * @author mpz5
 */

package Compiler.TokenParser.Nodes;

import Compiler.TokenParser.Token;
import java.util.List;

public class PowerNode extends Node {

    public PowerNode(Token t){
        super(t);
    }


    public double evaluate(){
        List<Node> children = getChildren();
        return Math.pow(Double.parseDouble(children.get(0).getTokenValue()), Double.parseDouble(children.get(1).getTokenValue()));
    }
}