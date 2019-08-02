/**
 * This class extends the ExecutableNode class and is extended by all structured nodes. Structured nodes include ForNode and MakeUserInstructionNode classes. These node types have specific syntax constraints that are not met by the basic checks that occur throughout the tree parsing. For example certain Nodes may need to have a list of purely variables or may need to have a list containing one variable and one constant. These functions check these things and are used in the SyntaxParser class.
 *
 * @author mpz5
 */

package Compiler.TokenParser.Nodes;

import Compiler.TokenParser.Token;

public abstract class StructuredNode extends ExecutableNode {

    public StructuredNode(Token t){
        super(t);
    }

    /**
     * This function is extended by all subclasses and throw an error if the syntax validation does not pass.
     */
    public abstract void validateSyntax();


}
