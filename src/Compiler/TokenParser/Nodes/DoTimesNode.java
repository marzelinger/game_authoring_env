/**
 * @author mpz5
 */
package Compiler.TokenParser.Nodes;

import Compiler.SyntaxParser;
import Compiler.TokenParser.Token;
import Compiler.TokenParser.TokenType;
import Compiler.Turtle;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class DoTimesNode extends StructuredNode {

    public static final int ITERATOR_LIST_INDEX = 0;
    public static final int COMMANDS_LIST_INDEX = 1;

    private Token t;

    public DoTimesNode(Token t) {
        super(t);
        this.t = t;

    }

    public double evaluate() {
        return getChildren().get(COMMANDS_LIST_INDEX).evaluate();
    }

    public void validateSyntax() {
        if (!(getChildren().get(ITERATOR_LIST_INDEX).checkType(TokenType.ListStart) && getChildren().get(ITERATOR_LIST_INDEX).checkType(TokenType.ListStart))) {
            throw new Error(SyntaxParser.SYNTAX_ERROR);
        }
    }

    private void resetCommands(Node variable, int i){
        List<Node> visitedNodes = new ArrayList<>();
        visitedNodes.add(getChildren().get(1));
        while(!visitedNodes.isEmpty()){
            Node currentNode = visitedNodes.get(0);
            if(currentNode.checkExecutable()){
                ((ExecutableNode)currentNode).resetExecuted();
            }
            for(Node child : currentNode.getChildren()){
                visitedNodes.add(child);
            }
            if(currentNode.checkType(TokenType.Variable)){
                if(currentNode.getTokenValue().equals(variable.getTokenValue())){
                    ((VariableNode)currentNode).assignValue(i);
                }
            }
            visitedNodes.remove(0);
        }
    }

    public Consumer<Turtle> executeNode(){
        Node variable = getChildren().get(0).getChildren().get(0);
        Node limit = getChildren().get(0).getChildren().get(1);
        int limitNum = Integer.parseInt(limit.getTokenValue());
        Node commandHead = getChildren().get(1);
        return turtle -> {
            for(int i = 0; i <= limitNum; i ++){
                resetCommands(variable, i);
                for(Node child : commandHead.getChildren()){
                    if(child.checkExecutable()){
                        ((ExecutableNode) child).execute().accept(turtle);
                    }
                }
            }
        };
    }




}
