package Compiler.TokenParser;

import java.util.*;
import java.util.regex.Pattern;

/**
 * Simple parser based on regular expressions that matches program strings to
 * kinds of language features.
 *
 * @author Robert C. Duvall
 * @author mpz5
 */
public class Tokenizer implements TokenizerInterface{
    public static final String SYNTAX_PROPERTIES = "languages.Syntax";

    // "types" and the regular expression patterns that recognize those types
    // note, it is a list because order matters (some patterns may be more generic)
    private List<Map.Entry<String, Pattern>> mySymbols;

    /**
     * Create an empty parser.
     */
    public Tokenizer() {
        mySymbols = new ArrayList<>();
    }

    /**
     * Adds the given resource file to this language's recognized types
     */
    public void addResource(String resource) {
        var resources = ResourceBundle.getBundle(resource);
        for (var key : Collections.list(resources.getKeys())) {
            var regex = resources.getString(key);
            mySymbols.add(new AbstractMap.SimpleEntry<>(key,
                    // THIS IS THE IMPORTANT LINE
                    Pattern.compile(regex, Pattern.CASE_INSENSITIVE)));
        }
        System.out.println(mySymbols.toString());
    }

    public void changeLanguage(String lang){
        mySymbols = new ArrayList<>();
        addResource(lang);
        addSyntax();
    }

    public void addSyntax(){
        addResource(SYNTAX_PROPERTIES);
    }
    /**
     * Returns language's type associated with the given text if one exists
     */
    public Token getSymbol(String text) {
        final var ERROR = "NO MATCH";
        for (var e : mySymbols) {
            if (match(text, e.getValue())) {
                return new Token(e.getKey(), text);
            }
        }
        throw new Error(ERROR);
    }

    /**
     * Returns true if the given text matches the given regular expression pattern
     */
    private boolean match(String text, Pattern regex) {
        // THIS IS THE IMPORTANT LINE
        return regex.matcher(text).matches();
    }

    public String removeComments(String text) {
        Pattern comments = Pattern.compile("#.*", Pattern.CASE_INSENSITIVE);
        String[] lines = text.split("\n");
        String textWithoutComments = "";
        for (int i = 0; i < lines.length; i++) {
            String line = lines[i];
            if (!comments.matcher(line).matches()) {
                textWithoutComments = textWithoutComments + line + "\n";
            }
        }
        return textWithoutComments;
    }

}
