package Compiler.TokenParser;

public interface TokenizerInterface {
    void addResource(String resource);
    void changeLanguage(String lang);
    void addSyntax();
    String removeComments(String text);
    Token getSymbol(String text);
}
