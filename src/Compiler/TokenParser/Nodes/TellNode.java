package Compiler.TokenParser.Nodes;

import Compiler.TokenParser.Token;
import Compiler.TokenParser.TokenType;
import Compiler.TurtleManager;

import Compiler.Turtle;
import java.util.List;
import java.util.function.Consumer;

import Compiler.Turtle;


public class TellNode extends StructuredNode {

    TurtleManager tsManager;


    public TellNode(Token t, TurtleManager tm) {
        super(t);
        this.tsManager = tm;
    }

    //returns last value from last turtle in the turtles list

    public double evaluate() {

            int lastChildNode = getChildren().size() - 1;
            return getChildren().get(lastChildNode).evaluate();

    }

    public void validateSyntax(){
        if(!getChildren().get(0).checkType(TokenType.ListStart)){
            throw new Error("Invalid Syntax");
        }

    }


    public Consumer<Turtle> executeNode() {
        //if call upon a turtle ID that has not existed, create that new turtle and place on home

            //resets active list
            //deactivate all turtles, then activate the ones that you need
            List<Turtle> allTurtles = tsManager.getAllTurtles();
            for(Turtle t : allTurtles){
                t.deactivateTurtle();
            }

            int numLeaves = getChildren().get(0).getChildren().size();
            for (Node child : getChildren().get(0).getChildren()) {

                if(child.checkType(TokenType.ListEnd)){
                    break;
                }

                int id = Integer.parseInt(child.getTokenValue());


                if (!tsManager.checkTurtleExists(id)) {
                    tsManager.createTurtle(id);
                }
                tsManager.activateTurtle(id);
            }





        return turtle -> {
        };
    }
}
