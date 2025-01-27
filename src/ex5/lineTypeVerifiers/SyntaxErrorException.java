package ex5.lineTypeVerifiers;

public class SyntaxErrorException extends IncorrectLineException {
    public SyntaxErrorException(String message, int lineNum) {
        super(message, lineNum);
    }
}
