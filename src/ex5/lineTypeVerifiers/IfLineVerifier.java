package ex5.lineTypeVerifiers;

import ex5.Containers.VariableContainer;
import ex5.main.LineVerifier;
import ex5.utils.LineContent;
import ex5.utils.Constants;
import ex5.utils.LineNumberTuple;
import ex5.utils.RegexConstants;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This class verifies that a line is an if statement.
 * @author Omry Mor, Ruth Schiller
 */
public class IfLineVerifier implements LineTypeVerifier {

    private final int expressionStringGroup = 1;

    /**
     * Verify that the line is an if statement.
     * @param lineNumberTuple the line number and the line content
     * @return true if the line is an if statement, false otherwise
     */
    @Override
    public boolean verifyLine(LineNumberTuple lineNumberTuple) throws IncorrectLineException {
        Pattern pattern = Pattern.compile(RegexConstants.IF_STATEMENT_REGEX);
        Matcher matcher = pattern.matcher(lineNumberTuple.line);
        if (!matcher.find()) {
            return false;
        }

        if(VariableContainer.inGlobalScope() && !LineVerifier.isFirstPass){
            throw new LanguageRuleException(Constants.CALL_NOT_IN_FUNCTION, lineNumberTuple.lineNumber);
        }
        VariableContainer.scopeIn();
        if(LineVerifier.isFirstPass){
            return true;
        }
        String expressionString = matcher.group(expressionStringGroup);
        try{
            verifyExpressions(expressionString, lineNumberTuple.lineNumber, LineContent.IF_STATEMENT);
            return true;
        } catch (IncorrectLineException e) {
            throw e;
        }
    }
}
