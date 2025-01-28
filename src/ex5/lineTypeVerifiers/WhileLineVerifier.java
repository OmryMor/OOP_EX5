package ex5.lineTypeVerifiers;

import ex5.Containers.PreviousStatementContainer;
import ex5.Containers.VariableContainer;
import ex5.main.LineVerifier;
import ex5.utils.LineContent;
import ex5.utils.Constants;
import ex5.utils.LineNumberTuple;
import ex5.utils.RegexConstants;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This class verifies that a line is a while statement.
 * @author Omry Mor, Ruth Schiller
 */
public class WhileLineVerifier implements LineTypeVerifier{

    private final int expressionStringGroup = 1;

    /**
     * Verify that the line is a while statement.
     * @param lineNumberTuple the line number and the line content
     * @return true if the line is a while statement, false otherwise
     */
    @Override
    public boolean verifyLine(LineNumberTuple lineNumberTuple) {
        Pattern pattern = Pattern.compile(RegexConstants.WHILE_STATEMENT_REGEX);
        Matcher matcher = pattern.matcher(lineNumberTuple.line);
        if (!matcher.find()) {
            return false;
        }
        if(VariableContainer.inGlobalScope()){
            throw new LanguageRuleException(Constants.CALL_NOT_IN_FUNCTION, lineNumberTuple.lineNumber);
            // TODO return false;
        }
        VariableContainer.scopeIn();
        if(!LineVerifier.isFirstPass){
            return true;
        }
        String expressionString = matcher.group(expressionStringGroup);
        try{
            verifyExpressions(expressionString, lineNumberTuple.lineNumber, LineContent.WHILE_STATEMENT);
        } catch (IncorrectLineException e) {
            throw new RuntimeException(e);
        }
        // TODO return false;
        PreviousStatementContainer.setPrevStatement(LineContent.WHILE_STATEMENT);
        return true;
    }
}
