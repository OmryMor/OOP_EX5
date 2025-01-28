package ex5.lineTypeVerifiers;

/**
 * This class represents an exception that is thrown when a line is incorrect.
 * @author Omry Mor, Ruth Schiller
 */
public class IncorrectLineException extends Exception {
    /**
     * Constructor - creates a new IncorrectLineException with a message
     *
     * @param message the message of the exception
     */
    public IncorrectLineException(String message, int lineNum) {
        super(String.format(message + " (line %d).", lineNum));
    }
}
