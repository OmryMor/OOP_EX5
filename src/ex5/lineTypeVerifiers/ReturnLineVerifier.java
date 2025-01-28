package ex5.lineTypeVerifiers;

import ex5.Containers.PreviousStatementContainer;
import ex5.Containers.VariableContainer;
import ex5.utils.LineContent;
import ex5.utils.Constants;
import ex5.utils.LineNumberTuple;
import ex5.utils.RegexConstants;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This class verifies that a line is a return statement.
 * @author Omry Mor, Ruth Schiller
 */
public class ReturnLineVerifier implements LineTypeVerifier{

    /**
     * Verify that the line is a return statement.
     * @param lineNumberTuple the line number and the line content
     * @return true if the line is a return statement, false otherwise
     */
    @Override
    public boolean verifyLine(LineNumberTuple lineNumberTuple) throws IncorrectLineException {
        Pattern pattern = Pattern.compile(RegexConstants.RETURN_REGEX);
        Matcher matcher = pattern.matcher(lineNumberTuple.line);
        if (!matcher.find()) {
            return false;
        }
        if(VariableContainer.inGlobalScope()){
            throw new LanguageRuleException(Constants.CALL_NOT_IN_FUNCTION, lineNumberTuple.lineNumber);
        }
        PreviousStatementContainer.setPrevStatement(LineContent.RETURN);
        return true;
    }
}
