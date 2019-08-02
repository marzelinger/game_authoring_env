package Compiler.TokenParser.Nodes;


import Compiler.TokenParser.Token;
import Compiler.TokenParser.TokenType;

import java.util.HashMap;
import Compiler.Turtle;

import java.util.List;
import java.util.function.Consumer;

public class ForNode extends StructuredNode {
    public static final int ITERATOR_LIST_INDEX = 0;
    public static final int COMMANDS_LIST_INDEX = 1;

    private Token t;

    public ForNode(Token t) {
        super(t);
        this.t = t;
    }


    public double evaluate() {
            return getChildren().get(COMMANDS_LIST_INDEX).evaluate();

    }

    public void validateSyntax() {

        //should be a list start node with two children, if this is not true, throw the error
        if (!(getChildren().get(ITERATOR_LIST_INDEX).checkType(TokenType.ListStart) && getChildren().get(ITERATOR_LIST_INDEX).checkType(TokenType.ListStart))) {
            throw new Error("Invalid Syntax");
        }
    }



    public Consumer<Turtle> executeNode() {

        Consumer<Turtle> action;

            HashMap<String, List<Node>> savedVars = new HashMap<String, List<Node>>();
            VariableNode iterator = new VariableNode(t, savedVars);


            action = turtle -> {

                int start = (int) getChildren().get(ITERATOR_LIST_INDEX).getChildren().get(1).evaluate();

                int end = (int) getChildren().get(ITERATOR_LIST_INDEX).getChildren().get(2).evaluate();


                int incr = (int) getChildren().get(ITERATOR_LIST_INDEX).getChildren().get(3).evaluate();


                for(int j = start; j < end; j+=incr) {

                    for (Node child : getChildren().get(COMMANDS_LIST_INDEX).getChildren()) {
                        if (child.checkExecutable()) {
                            ((ExecutableNode) child).resetExecuted();
                            ((ExecutableNode) child).execute().accept(turtle);
                        }
                    }

                    //updating the variable
                    iterator.assignValue(j);
                }


            };


        return action;
    }


}


