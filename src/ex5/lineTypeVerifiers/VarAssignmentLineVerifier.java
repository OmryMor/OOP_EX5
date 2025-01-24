package ex5.lineTypeVerifiers;

import ex5.Containers.PreviousStatementContainer;
import ex5.utils.LineContent;
import ex5.utils.LineNumberTuple;

/**
 * This class verifies that a line is a variable assignment line.
 * @author Omry Mor, Ruth Schiller
 */
public class VarAssignmentLineVerifier implements LineTypeVerifier{

    /**
     * Verify that the line is a variable assignment line.
     * @param lineNumberTuple the line number and the line content
     * @return true if the line is a variable assignment line, false otherwise
     */
    @Override
    public boolean verifyLine(LineNumberTuple lineNumberTuple) {

        PreviousStatementContainer.setPrevStatement(LineContent.VAR_ASSIGNMENT);
        return true;
    }
}
