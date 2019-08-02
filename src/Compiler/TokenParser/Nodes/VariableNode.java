/**
 * @author mpz5
 */
package Compiler.TokenParser.Nodes;

import Compiler.SyntaxParser;
import Compiler.TokenParser.Token;

import java.util.List;
import java.util.Map;

public class VariableNode extends Node {
    Map<String, List<Node>> values;
    Double val;

    public VariableNode(Token t, Map<String, List<Node>> savedVars){
        super(t);

        values = savedVars;
        val = null;

    }

    /**
     * This value will only apply to this node for one call to evaluate. Used for the case when a predefined global
     * variable is used as a function param. This should allow this variable to be used within the function as the param
     * value without impacting how it acts outside of the function.
     * @param value
     */
    public void assignValue(double value){
        val = value;
    }

    public double evaluate(){
        if(val!=null){
            double returnValue = val;
            return returnValue;
        }
        if(values.containsKey(getTokenValue())){
            return Double.parseDouble(values.get(getTokenValue()).get(0).getTokenValue());
        }
        throw new Error(SyntaxParser.SYNTAX_ERROR);
    }
}
