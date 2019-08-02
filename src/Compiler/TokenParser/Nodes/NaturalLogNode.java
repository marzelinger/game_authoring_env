/**
 * @author mpz5
 */

package Compiler.TokenParser.Nodes;

import Compiler.TokenParser.Token;

import java.util.List;

public class NaturalLogNode extends Node {

    public NaturalLogNode(Token t){
        super(t);
    }


    public double evaluate(){
        List<Node> children = getChildren();
        return Math.log(Double.parseDouble(children.get(0).getTokenValue()));
    }
}