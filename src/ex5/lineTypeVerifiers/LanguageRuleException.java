package ex5.lineTypeVerifiers;

public class LanguageRuleException extends IncorrectLineException {
    public LanguageRuleException(String message, int lineNum) {
        super(message, lineNum);
    }
}
