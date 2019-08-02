/**
 * This class uses a NodeFactor instance to build a Parse Tree out of a list of tokens.
 * @see https://en.wikipedia.org/wiki/Parse_tree for the definition of a Parse Tree
 * The process of creating a tree consists of:
 *      Creating nodes out of Tokens using the NodeFactory class
 *      Building a tree determined by the number of arguments required by each type of Node
 *      Checking syntax while building the tree, as well as after building the tree for particularly structured nodes.
 *
 * This class is well designed because almost all of its functionality is encapsulated. It is a shy class, with only one public method. All other functionality is private. In addition, this class has no getters or setters. It is an extremely active class and is not used as a wrapper for carrying state. Finally, functions are short and all have one specific purpose. Overall, the class has one clear functionality and doesn't try to accomplish many different things.
 *
 * @author mpz5
 */

package Compiler;

import Compiler.TokenParser.NodeFactory;
import Compiler.TokenParser.NodeFactoryInterface;
import Compiler.TokenParser.Nodes.Node;
import Compiler.TokenParser.Nodes.StructuredNode;
import Compiler.TokenParser.Token;
import Compiler.TokenParser.TokenType;

import java.util.*;


/**
 * This class takes in a list of tokens and creates a parse tree out of them. It is used to perform syntax analysis on the code as well as create a tree structure that will allow for the correct execution of the code. This class uses the NodeFactory class to create Nodes. The tree that is created is then used by the Executor class.
 *
 * @author mpz5
 */
public class SyntaxParser implements SyntaxParserInterface {
    public static final String SYNTAX_ERROR = "Invalid Syntax";
    private NodeFactoryInterface nf;

    public SyntaxParser(NodeFactoryInterface factory){
        nf = factory;
    }

    /**
     * This function takes a list of tokens and the current turtle and builds
     * @param inputTokens
     * @param currTurtle
     * @return
     */
    public List<Node> buildTrees(List<Token> inputTokens, Turtle currTurtle){
        List<Node> trees = new ArrayList<>();
        for(int i = 0; i < inputTokens.size(); i ++){
            Node tree = buildTree(i, inputTokens, currTurtle);
            i = i + getTreeSize(tree) - 1;
            trees.add(tree);
        }
        for(Node tree : trees){
            checkSyntax(tree);
        }
        return trees;
    };

    /**
     * This function iterates through every node in the tree recursively and validates the syntax if the node is a structured node. Basic syntax checking (that functions get the correct number of arguments and that there is not a ListEnd before a ListStart are done during the building of the tree.
     * @param treeRoot root of the tree being checked for syntax
     */
    private void checkSyntax(Node treeRoot){
        if(treeRoot.checkStructured()){
            ((StructuredNode)treeRoot).validateSyntax();
        }
        for(Node child : treeRoot.getChildren()){
            checkSyntax(child);
        }

    }

    /**
     * This function builds a parse tree using the list of input tokens. It iterates through the list of tokens building each node's children's sub trees recursively then attaching those subtrees to the roots.
     * @param index index of token currently being looked at
     * @param inputTokens list of token that are being used to create nodes and build tree
     * @param currTurtle the turtle that will be operated on by this tree
     * @return
     */
    private Node buildTree(int index, List<Token> inputTokens, Turtle currTurtle){
        Token t = inputTokens.get(index);
        Node root = null;
        try{
            root = nf.createNode(t, currTurtle);
        } catch (Exception e){
            // messages are specified in the nf class to clearly indicate the reason for the exception
            throw new Error(e);
        }
        int numArgs = root.getNumArgs();
        if(numArgs == 0 || inputTokens.size() <= 0){
            return root;
        }
        boolean insideList = t.checkTypeEquality(TokenType.ListStart);
        addChildren(numArgs, index, inputTokens, insideList, currTurtle, root);
        return root;
    }

    /**
     * This function builds and adds the child subtrees to the root node passed.
     * @param numArgs the number of arguments that the root node requires. This translates to the number f children in the Tree
     * @param index the current index in the inputTokens list
     * @param inputTokens a list of tokens
     * @param insideList a boolean signifying whether or not the root node is inside of a list in the original command or not
     * @param currTurtle the turtle that will be operated on by this tree
     * @param root the current root node
     */
    private void addChildren(int numArgs, int index, List<Token> inputTokens, boolean insideList, Turtle currTurtle, Node root){
        for(int i = 0; i < numArgs; i = insideList ? i : i + 1){
            index = index + 1;
            if(index < inputTokens.size()){
                Token childToken = inputTokens.get(index);
                i = checkListEnd(childToken, insideList) ? numArgs : i;
                Node childTree = buildTree(index, inputTokens, currTurtle);
                root.addChild(childTree);
                int numTokensInChild = getTreeSize(childTree);
                index = index + numTokensInChild - 1;
            }
            else{
                throw new Error(SYNTAX_ERROR);
            }
        }
    }

    /**
     * This function determines if a list that a token is currently inside of is ended by the token passed.
     * @param token the token that could potentially be ending a list
     * @param insideList a boolean signifying whether or not the root node is inside of a list in the original command or not
     * @return
     */
    private boolean checkListEnd(Token token, boolean insideList){
        if(token.checkTypeEquality(TokenType.ListEnd)){
            if(insideList){
                return true;
            }
            else {
                throw new Error(SYNTAX_ERROR);
            }
        }
        return false;
    }

    /**
     * Given the root node of a tree this function determines the total number of nodes in the tree. This function is used to avoid repeatedly creating nodes out of the same Tokens and adding them to the tree multiple times.
     * @param root root node of the tree
     * @return
     */
    private int getTreeSize(Node root){
        int numTokens = 0;
        List<Node> visitedNodes = new ArrayList<>();
        visitedNodes.add(root);
        while(!visitedNodes.isEmpty()){
            numTokens++;
            Node currentNode = visitedNodes.get(0);
            for(Node child : currentNode.getChildren()){
                visitedNodes.add(child);
            }
            visitedNodes.remove(0);
        }
        return numTokens;
    }

}
