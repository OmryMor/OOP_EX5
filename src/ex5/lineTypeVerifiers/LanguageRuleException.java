package ex5.lineTypeVerifiers;

/**
 * This class represents an exception that is thrown when a line is incorrect.
 * @author Omry Mor, Ruth Schiller
 */
public class LanguageRuleException extends IncorrectLineException {

    /**
     * Constructor - creates a new IncorrectLineException with a message
     * @param message the message of the exception
     */
    public LanguageRuleException(String message, int lineNum) {
        super(message, lineNum);
    }
}
