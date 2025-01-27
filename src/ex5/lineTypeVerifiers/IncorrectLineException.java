package ex5.lineTypeVerifiers;

// TODO make sure right exception type this time!
public class IncorrectLineException extends RuntimeException {

    public IncorrectLineException(String message, int lineNum) {
        super(String.format(message + " (line %d).", lineNum));
    }
}
