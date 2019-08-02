/**
 * @author mpz5
 */

package Compiler.TokenParser.Nodes;

import Compiler.TokenParser.Token;

import java.util.List;

public class SineNode extends Node {

    public SineNode(Token t){
        super(t);
    }


    public double evaluate(){
        List<Node> children = getChildren();
        return Math.sin(children.get(0).evaluate());
    }
}
