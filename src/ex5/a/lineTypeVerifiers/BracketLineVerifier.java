package ex5.a.lineTypeVerifiers;

import ex5.a.Containers.PreviousStatementContainer;
import ex5.a.Containers.VariableContainer;
import ex5.a.LineContent;
import ex5.utils.Constants;
import ex5.utils.LineNumberTuple;

public class BracketLineVerifier implements LineTypeVerifier {
    @Override
    public boolean verifyLine(LineNumberTuple lineNumberTuple) {
        if(!lineNumberTuple.line.trim().equals("}")){
            return false;
        }
        if(!VariableContainer.scopeOut()){
            //TODO Cant go to an outer scope error
            System.err.printf((Constants.ILLEGAL_SCOPE_ERROR), lineNumberTuple.lineNumber);
            return false;
        }

        if(VariableContainer.inGlobalScope()){
            if(PreviousStatementContainer.getPrevStatement() != LineContent.RETURN){
                //TODO last command wasnt return statement error
                System.err.printf((Constants.METHOD_NOT_ENDING_WITH_RETURN_ERROR), lineNumberTuple.lineNumber);
                return false;
            }
        }
        PreviousStatementContainer.setPrevStatement(LineContent.CLOSE_BRACKET);
        return true;
    }
}
