/**
 * This class is used to create instances of the the abstract Node class's subclasses. This class was designed using the Factory design pattern and therefore removes any need for there to be logic about creating nodes anywhere else in the codebase for this project. This class also uses reflection to retrieve the correct constructor and parameters for each type of node. In addition, it determines the mapping of TokenTypes to Node Classes using a resource file. This makes it extremely easy for a new node to be added. In order to make this factory capable of creating a new type of node:
 *
 * 1. The new TokenType must be added into the TokenType class and placed in the map variables for the Token class
 * 2. A Node class must be created
 * 3. The new TokenType must be added to the node resource file used along with the Node class
 *
 * @author mpz5
 */


package Compiler.TokenParser;

import Compiler.TokenParser.Nodes.Node;

import java.lang.reflect.Constructor;
import java.util.*;
import Compiler.Turtle;
import Compiler.TurtleManager;

public class NodeFactory implements NodeFactoryInterface{
    public static final String PATH = "Compiler.TokenParser.Nodes.";
    public static final String PROPERTY_SEPARATOR = ",";
    public static final String SAVED_FUNCTIONS_MAP_KEY = "SavedFunctionsMap";
    public static final String SAVED_VARIABLES_MAP_KEY = "SavedVariablesMap";
    public static final String NODE_CLASS_ERROR = "Multiple Constructors Exist. Error.";
    public static final String NODE_FILE_ERROR = "Node type is not present in resource file.";
    public static final String NODE_CONSTRUCTOR_NOT_HANDLED_ERROR = "Currently, this node constructor needs arguments that are not handled";
    Map<String, Map<String, List<Node>>> savedMaps;
    Map<String, String> nodeMappings;
    TurtleManager tsManager;


    public NodeFactory(String resource, Map<String, List<Node>> savedFunctions, Map<String,List<Node>> savedVariables, TurtleManager tsm){
        nodeMappings = generateNodeMappings(resource);
        savedMaps = new HashMap<>();
        savedMaps.put(SAVED_FUNCTIONS_MAP_KEY, savedFunctions);
        savedMaps.put(SAVED_VARIABLES_MAP_KEY, savedVariables);
        tsManager = tsm;
    }

    /**
     * This function reads the resource file passed into a map containing information about each type of Node. The resulting map has a key value that is a property in the resource file. In this case, these should be TokenTypes. The value associated with each of these properties is the string value in the file and contains the name of the Node class associated with the TokenType and any other additional information.
     * @param resource
     * @return
     */
    private Map<String, String> generateNodeMappings(String resource){
        Map<String, String> nodeMap = new HashMap<>();
        ResourceBundle resources = ResourceBundle.getBundle(resource);
        for (String key : Collections.list(resources.getKeys())) {
            String info = resources.getString(key);
            nodeMap.put(key, info);
        }
        return nodeMap;
    }

    /**
     * This function is called by the SyntaxParse command to create a node given a particular token and current turtle. The function calls helper functions getConstructor and createConstructorInstance, which use reflection to retrieve and instantiate the correct class. The mappings of TokenType to Node Classes are determined by the nodeMappings map, which is created in the constructor of this cass by the generateNodeMappings function.
     * @param t token used to create the node
     * @param currTurtle the current turtle that this node will operate on.
     * @return
     * @throws Exception
     */
    @Override
    public Node createNode(Token t, Turtle currTurtle) throws Exception{
        if(!nodeMappings.containsKey(t.toString())){
            throw new Error(NODE_FILE_ERROR);
        }
        String[] info = nodeMappings.get(t.toString()).split(PROPERTY_SEPARATOR);
        Constructor constructor = getConstructor(info[0]);
        Node node = createConstructorInstance(t, currTurtle, constructor, info);
        if(node == null){
            throw new Error(NODE_CONSTRUCTOR_NOT_HANDLED_ERROR);
        }
        return node;
    }

    /**
     * This function gets the parameter types from the given constructor and uses these, as well as information passed to create the correct call to the constructor. The node created is then returned. If the function returns null this means the Node class has a constructor requiring a combination of constructors not currently handled.
     * @param t token to be passed as a parameter to the Node
     * @param currTurtle current turtle which is a potential parameter
     * @param constructor constructor function that will be called
     * @param info information about this token type from the Node resource file
     * @return a new node
     * @throws Exception
     */
    private  Node createConstructorInstance(Token t, Turtle currTurtle, Constructor constructor, String[] info) throws Exception{
        Node treeNode = null;
        Class<?>[] pType = constructor.getParameterTypes();
        if(pType.length == 1){
            treeNode = (Node)constructor.newInstance(t);
        }
        else{
            if(pType[1] == Map.class){
                treeNode = (Node)constructor.newInstance(t, savedMaps.get(info[1].trim()));
            }
            else if(pType[1] == Turtle.class){
                treeNode = (Node)constructor.newInstance(t, currTurtle);
            }
            else if(pType[1] == TurtleManager.class){
                treeNode = (Node)constructor.newInstance(t, tsManager );
            }
        }
        return treeNode;
    }

    /**
     * This function uses reflection to get the Class and constructor based on the className passed as a parameter.
     * @param className name of class
     * @return constructor for class
     * @throws Exception
     */
    private Constructor getConstructor(String className) throws Exception{
        Class c = Class.forName(PATH + className);
        Constructor[] allConstructors = c.getDeclaredConstructors();
        if(allConstructors.length > 1){
            throw new Error(NODE_CLASS_ERROR);
        }
        return allConstructors[0];
    }
}
