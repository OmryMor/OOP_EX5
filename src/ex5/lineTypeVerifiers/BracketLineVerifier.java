package ex5.lineTypeVerifiers;

import ex5.Containers.PreviousStatementContainer;
import ex5.Containers.VariableContainer;
import ex5.main.LineVerifier;
import ex5.utils.LineContent;
import ex5.utils.Constants;
import ex5.utils.LineNumberTuple;

/**
 * This class verifies that a line is a closing bracket.
 * @author Omry Mor, Ruth Schiller
 */
public class BracketLineVerifier implements LineTypeVerifier {

    private static final String CLOSE_BRACKET = "}";

    /**
     * Verify that the line is a closing bracket.
     * @param lineNumberTuple the line number and the line content
     * @return true if the line is a closing bracket, false otherwise
     */
    @Override
    public boolean verifyLine(LineNumberTuple lineNumberTuple) {
        // check if line of type bracket line
        if(!lineNumberTuple.line.trim().equals(CLOSE_BRACKET)){
            return false;
        }
        if(!VariableContainer.scopeOut()){
            throw new LanguageRuleException(Constants.ILLEGAL_SCOPE_ERROR, lineNumberTuple.lineNumber);
            // TODO return false;
        }
        if(VariableContainer.inGlobalScope()){
            if(PreviousStatementContainer.getPrevStatement() != LineContent.RETURN){
                throw new LanguageRuleException(Constants.METHOD_NOT_ENDING_WITH_RETURN_ERROR,
                        lineNumberTuple.lineNumber);
                // TODO return false;
            }
        }
        PreviousStatementContainer.setPrevStatement(LineContent.CLOSE_BRACKET);
        return true;
    }
}
