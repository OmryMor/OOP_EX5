package ex5.lineTypeVerifiers;

import ex5.Containers.PreviousStatementContainer;
import ex5.Containers.VariableAttributes;
import ex5.Containers.VariableContainer;
import ex5.utils.*;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This class verifies that a line is a variable assignment line.
 * @author Omry Mor, Ruth Schiller
 */
public class VarAssignmentLineVerifier implements LineTypeVerifier{

    private int varAssignmentGroup = 1;
    private final int singleAssignNameGroup = 2;
    private final int singleAssignValueGroup = 5;

    /**
     * Verify that the line is a variable assignment line.
     * @param lineNumberTuple the line number and the line content
     * @return true if the line is a variable assignment line, false otherwise
     */
    @Override
    public boolean verifyLine(LineNumberTuple lineNumberTuple) throws IncorrectLineException {

        // check if this is an assignment statement, else return false
        Pattern pattern = Pattern.compile(RegexConstants.VAR_ASSIGNMENT_REGEX);
        Matcher matcher = pattern.matcher(lineNumberTuple.line);
        if (!matcher.find()) {
            return false;
        }

        pattern = Pattern.compile(RegexConstants.SINGLE_VAR_ASSIGN_REGEX);
        matcher = pattern.matcher(matcher.group(varAssignmentGroup));
        while (matcher.find()) {
            String name = matcher.group(singleAssignNameGroup);
            String value = matcher.group(singleAssignValueGroup);
            // check if var declared
            VariableAttributes varAttr = VariableContainer.getVar(name);
            if (varAttr == null)
            {
                throw new LanguageRuleException(Constants.UNDECLARED_VAR_ERROR, lineNumberTuple.lineNumber);
            }
            // check if final!
            else if (varAttr.isFinal)
            {
                throw new LanguageRuleException(Constants.FINAL_VAR_ASSIGN_ERROR, lineNumberTuple.lineNumber);
            }

            // check if type matches
            try{
                verifyValue(varAttr.type, value, lineNumberTuple.lineNumber);
            } catch (IncorrectLineException e) {
                throw e;
            }
            // assign to var
            if (VariableContainer.getVarInScope(varAttr.name) == null) {
                VariableAttributes newVar = new VariableAttributes(
                        varAttr.type,
                        true,
                        false,
                        varAttr.name);
                if (!VariableContainer.addVarToCurrentScope(newVar)) {
                    throw new LanguageRuleException(Constants.VAR_NAME_TAKEN_ERROR,
                            lineNumberTuple.lineNumber);
                }
            }
            else
            {
                varAttr.hasValue = true;
            }
        }
        PreviousStatementContainer.setPrevStatement(LineContent.VAR_ASSIGNMENT);
        return true;
    }
}
