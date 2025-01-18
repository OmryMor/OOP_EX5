package ex5.a.lineTypeVerifiers;

import ex5.a.Containers.PreviousStatementContainer;
import ex5.a.LineContent;
import ex5.utils.LineNumberTuple;
import ex5.a.Containers.Scope;

public class VarAssignmentLineVerifier implements LineTypeVerifier{
    @Override
    public boolean verifyLine(LineNumberTuple lineNumberTuple) {

        PreviousStatementContainer.setPrevStatement(LineContent.VAR_ASSIGNMENT);
        return true;
    }
}
