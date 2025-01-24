package ex5.a.lineTypeVerifiers;

import ex5.a.Containers.VariableContainer;
import ex5.a.LineContent;
import ex5.utils.Constants;
import ex5.utils.LineNumberTuple;
import ex5.utils.RegexConstants;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This class verifies that a line is an if statement.
 * @author Omry Mor, Ruth Schiller
 */
public class IfLineVerifier implements LineTypeVerifier{

    private final int expressionStringGroup = 1;

    /**
     * Verify that the line is an if statement.
     * @param lineNumberTuple the line number and the line content
     * @return true if the line is an if statement, false otherwise
     */
    @Override
    public boolean verifyLine(LineNumberTuple lineNumberTuple) {
        Pattern pattern = Pattern.compile(RegexConstants.IF_STATEMENT_REGEX);
        Matcher matcher = pattern.matcher(lineNumberTuple.line);
        if (!matcher.find()) {
            return false;
        }
        if(VariableContainer.inGlobalScope()){
            //todo it is being called outside of a function -> error
            System.err.printf((Constants.CALL_NOT_IN_FUNCTION), lineNumberTuple.lineNumber);
            return false;
        }
        VariableContainer.scopeIn();
        String expressionString = matcher.group(expressionStringGroup);
        return verifyExpressions(expressionString, lineNumberTuple.lineNumber, LineContent.IF_STATEMENT);
    }
}
