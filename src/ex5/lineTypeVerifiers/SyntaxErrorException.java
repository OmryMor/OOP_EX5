package ex5.lineTypeVerifiers;

/**
 * This class represents an exception that is thrown when a line has incorrect syntax
 * @author Omry Mor, Ruth Schiller
 */
public class SyntaxErrorException extends IncorrectLineException {

    /**
     * Constructor - creates a new IncorrectLineException with a message
     * @param message the message of the exception
     */
    public SyntaxErrorException(String message, int lineNum) {
        super(message, lineNum);
    }
}
