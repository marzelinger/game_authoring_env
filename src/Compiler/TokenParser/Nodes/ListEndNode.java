/**
 * @author mpz5
 */

package Compiler.TokenParser.Nodes;

import Compiler.TokenParser.Token;

public class ListEndNode extends Node{

    public ListEndNode(Token t){
        super(t);
    }

    public double evaluate() {
        return 0;
    }

}
