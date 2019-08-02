/**
 * @author mpz5
 */

package Compiler.TokenParser.Nodes;

import Compiler.TokenParser.Token;

public class ConstantNode extends Node {

    public ConstantNode(Token t){
        super(t);
    }

    public double evaluate(){
        return Double.parseDouble(getTokenValue());
    }

}
