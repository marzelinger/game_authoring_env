package Compiler.TokenParser.Nodes;

import Compiler.TokenParser.Token;
import Compiler.TokenParser.TokenType;
import Compiler.TurtleManager;
import Compiler.Turtle;
import Compiler.TurtleStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class AskNode extends StructuredNode {

    TurtleManager tsManager;
    public static final int TURTLES_LIST_INDEX = 0;
    public static final int COMMANDS_LIST_INDEX = 1;


    public AskNode(Token t, TurtleManager tsm) {
        super(t);
        this.tsManager = tsm;

    }

    public double evaluate() {

            int lastChildNode = getChildren().get(0).getChildren().size() - 1;
            return getChildren().get(lastChildNode).evaluate();

    }

    public void validateSyntax() {
        if (!getChildren().get(0).checkType(TokenType.ListStart) && !getChildren().get(1).checkType(TokenType.ListStart)) {
            throw new Error("Invalid Syntax");
        }
    }


    public Consumer<Turtle> executeNode() {

        //saving the original active turtles
//        List<Turtle> saveActiveTurts = tsManager.getActiveTurtles();
//        List<Integer> saveActiveIDs = new ArrayList<Integer>();
//
//        for(Turtle t: saveActiveTurts){
//                saveActiveIDs.add(t.getId());
//
//        }

        //deactivating all the turtles
        List<Turtle> allTurts = tsManager.getAllTurtles();
        for(Turtle t : allTurts){
            t.deactivateTurtle();
            //System.out.println("DeActivated turtle start: " + t.getId());
        }

        int numLeaves = getChildren().get(0).getChildren().size();
        for (Node turtle : getChildren().get(0).getChildren()) {

            if(turtle.checkType(TokenType.ListEnd)){
                break;
            }

            int id = Integer.parseInt(turtle.getTokenValue());

            if (!tsManager.checkTurtleExists(id)) {
                throw new Error("Turtle does not exist");
            }
            else{
                tsManager.activateTurtle(id);
            }

            //System.out.println("ask Activated turtle: " + id);
        }

        Consumer<Turtle> action = turtle -> {
            for (int j = 0; j < tsManager.getActiveTurtles().size(); j++) {
                applyCommandList();
            }
        };

        //restoring the original active turtles

//        for(Turtle t : allTurts){
//            t.deactivateTurtle();
//        }
//        for(Turtle t : allTurts){
//            for(int ID: saveActiveIDs){
//                if(t.getId() == ID){
//                    t.activateTurtle();
//
//                }
//            }
//        }

        return action;
    }

    public void applyCommandList() {
        Node n = getChildren().get(COMMANDS_LIST_INDEX);
        for (Node c : n.getChildren()) {
            if (c.checkExecutable()) {
                ((ExecutableNode) c).executeNode();
            } else {//just a query
                c.evaluate();
            }
        }

    }



}
