/**
 * @author mpz5
 */

package Compiler.TokenParser.Nodes;

import Compiler.TokenParser.Token;

import java.util.List;

public class MinusNode extends Node {

    public MinusNode(Token t){
        super(t);
    }


    public double evaluate(){
        List<Node> children = getChildren();
        return -1 * children.get(0).evaluate();
    }

}
