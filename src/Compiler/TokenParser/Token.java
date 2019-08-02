package Compiler.TokenParser;

import java.util.List;
import java.util.Map;

public class Token {
    public static final int SIGNIFY_USER_DEFINED = -2;

    /**
     * ListStart and ListEnd fall under the user defined category because all trees with lists will be formed by a ListStart node which will have as many children as the user defines. The ListEnd token will not be made into a node.
     */
    private List<TokenType> terminalTokens = List.of(TokenType.Variable, TokenType.Constant, TokenType.PenDown,TokenType.PenUp,TokenType.ShowTurtle,TokenType.HideTurtle,TokenType.Home,TokenType.ClearScreen,TokenType.XCoordinate,TokenType.YCoordinate, TokenType.Heading, TokenType.IsPenDown, TokenType.IsShowing, TokenType.Pi, TokenType.ListEnd,
            TokenType.SetPenColor, TokenType.SetShape, TokenType.ID, TokenType.Turtles);

    private List<TokenType> userDefinedTokens = List.of(TokenType.Command);

    private List<TokenType> turtleCommands = List.of(TokenType.Forward, TokenType.Backward, TokenType.Left, TokenType.Right, TokenType.SetHeading, TokenType.SetTowards, TokenType.SetPosition, TokenType.PenDown, TokenType.PenUp, TokenType.ShowTurtle, TokenType.HideTurtle, TokenType.Home, TokenType.ClearScreen);

    private List<TokenType> structuredTokens = List.of(TokenType.DoTimes, TokenType.For, TokenType.IfElse, TokenType.MakeVariable, TokenType.For, TokenType.Repeat, TokenType.If, TokenType.ListStart, TokenType.MakeUserInstruction,
            TokenType.Tell, TokenType.Ask, TokenType.AskWith, TokenType.Define);

    private Map<TokenType, Integer> commandArgumentNumber = Map.ofEntries(
            Map.entry(TokenType.Define, 2),
            Map.entry(TokenType.For, 2),
            Map.entry(TokenType.DoTimes, 2),
            Map.entry(TokenType.If, 2),
            Map.entry(TokenType.IfElse, 3),
            Map.entry(TokenType.MakeUserInstruction, 3),
            Map.entry(TokenType.Repeat, 2),
            Map.entry(TokenType.MakeVariable, 2),
            Map.entry(TokenType.Tell, 1),
            Map.entry(TokenType.Ask, 2),
            Map.entry(TokenType.AskWith, 2),
            Map.entry(TokenType.Forward, 1),
            Map.entry(TokenType.ListStart, 1),
            Map.entry(TokenType.Backward, 1),
            Map.entry(TokenType.Left, 1),
            Map.entry(TokenType.Right, 1),
            Map.entry(TokenType.SetHeading, 1),
            Map.entry(TokenType.Random, 1),
            Map.entry(TokenType.Sine, 1),
            Map.entry(TokenType.Cosine, 1),
            Map.entry(TokenType.Tangent, 1),
            Map.entry(TokenType.ArcTangent, 1),
            Map.entry(TokenType.NaturalLog, 1),
            Map.entry(TokenType.Not, 1),
            Map.entry(TokenType.Minus, 1),
            Map.entry(TokenType.SetTowards, 2),
            Map.entry(TokenType.SetPosition, 2),
            Map.entry(TokenType.Sum, 2),
            Map.entry(TokenType.Difference, 2),
            Map.entry(TokenType.Product, 2),
            Map.entry(TokenType.Quotient, 2),
            Map.entry(TokenType.Remainder, 2),
            Map.entry(TokenType.Power, 2),
            Map.entry(TokenType.LessThan, 2),
            Map.entry(TokenType.GreaterThan, 2),
            Map.entry(TokenType.Equal, 2),
            Map.entry(TokenType.NotEqual, 2),
            Map.entry(TokenType.And, 2),
            Map.entry(TokenType.Or, 2)
    );

    private boolean terminal;
    private TokenType type;
    private int numArgs;
    private String val;

    public Token(String tokenString, String value){
        type = TokenType.valueOf(tokenString);
        terminal = terminalTokens.contains(type);
        numArgs = determineNumArgs(type);
        val = value;
    }

    public boolean checkStructured(){
        return structuredTokens.contains(type);
    }

    public boolean checkExecutable(){
        return turtleCommands.contains(type) || checkStructured() || checkTypeEquality(TokenType.Command);
    }

    public int getNumArgs(){
        return numArgs;
    }

    public String toString(){
        return type.toString();
    }

    public String getValue(){
        return val;
    }

    public boolean checkTypeEquality(TokenType comparisonType){
        return comparisonType.name().equals(type.name());
    }

    public int determineNumArgs(TokenType tt){
        if(terminal) {
            return 0;
        }
        else if(userDefinedTokens.contains(tt)) {
            return SIGNIFY_USER_DEFINED;
        }
        else if(commandArgumentNumber.containsKey(type)) {
            return commandArgumentNumber.get(type);
        }
        else {
            return commandArgumentNumber.get(tt);
        }
    }

}