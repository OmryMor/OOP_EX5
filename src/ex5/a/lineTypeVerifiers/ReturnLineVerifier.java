package ex5.a.lineTypeVerifiers;

import ex5.a.Containers.PreviousStatementContainer;
import ex5.a.Containers.VariableContainer;
import ex5.a.LineContent;
import ex5.utils.Constants;
import ex5.utils.LineNumberTuple;
import ex5.utils.RegexConstants;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ReturnLineVerifier implements  LineTypeVerifier{
    @Override
    public boolean verifyLine(LineNumberTuple lineNumberTuple) {
        Pattern pattern = Pattern.compile(RegexConstants.RETURN_REGEX);
        Matcher matcher = pattern.matcher(lineNumberTuple.line);
        if (!matcher.find()) {
            return false;
        }
        if(VariableContainer.inGlobalScope()){
            //todo it is being called outside of a function -> error
            System.err.printf((Constants.CALL_NOT_IN_FUNCTION), lineNumberTuple.lineNumber);
            return false;
        }
        PreviousStatementContainer.setPrevStatement(LineContent.RETURN);
        return true;
    }
}
