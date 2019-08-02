/**
 * @author mpz5
 */

package Compiler.TokenParser.Nodes;

import Compiler.TokenParser.Token;

public class PiNode extends Node {
    public PiNode(Token t){
        super(t);
    }

    public double evaluate(){
        return Math.PI;
    }
}
