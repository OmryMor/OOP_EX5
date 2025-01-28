package ex5.lineTypeVerifiers;

import ex5.Containers.PreviousStatementContainer;
import ex5.Containers.VariableAttributes;
import ex5.Containers.VariableContainer;
import ex5.utils.LineContent;
import ex5.utils.VariableType;
import ex5.utils.Constants;
import ex5.utils.LineNumberTuple;
import ex5.utils.RegexConstants;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This interface is implemented by classes that verify the type of a line in the code.
 * @author Omry Mor, Ruth Schiller
 */
public interface LineTypeVerifier {

    /**
     * The group in the regex that contains the expression string
     */
    int singleExpressionGroup = 0;

    /**
     * Verify that the line is of the correct type.
     * @param lineNumberTuple the line number and the line content
     * @return true if the line is of the correct type, false otherwise
     */
    boolean verifyLine(LineNumberTuple lineNumberTuple) throws IncorrectLineException;

    /**
     * Verify that the expressions in the line are valid.
     * @param expressionString the expression string
     * @param lineNumber the line number
     * @param lineType the type of the line
     */
    default void verifyExpressions(String expressionString, int lineNumber, LineContent lineType)
            throws IncorrectLineException {
        if(expressionString == null){
            throw new LanguageRuleException(Constants.EMPTY_EXPRESSION_ERROR, lineNumber);
        }

        Pattern pattern = Pattern.compile(RegexConstants.CONDITION_REGEX);
        Matcher matcher = pattern.matcher(expressionString);
        while (matcher.find()){
            String expression = matcher.group(singleExpressionGroup);
            if(expression.equals(Constants.TRUE_KEYWORD) ||
                    expression.equals(Constants.FALSE_KEYWORD)){
                continue;
            }
            try {
                Integer.parseInt(expression);  // Check if it's an integer
                continue;
            } catch (NumberFormatException e) {
                try {
                    Double.parseDouble(expression);  // Check if it's a double
                    continue;
                } catch (NumberFormatException ex) {
                    // Not an int or a double, continue checking other options
                }
            }

            VariableAttributes var = VariableContainer.getVar(expression);
            if(var == null || !var.hasValue){
                throw new LanguageRuleException(Constants.UNINITIALIZED_VARAIBLE_IN_EXPRESSIOON, lineNumber);
            }

            if(var.type != VariableType.BOOLEAN &&
                    var.type != VariableType.DOUBLE &&
                    var.type != VariableType.INT){
                throw new LanguageRuleException(Constants.ILLEGAL_TYPE_IN_EXPRESSION, lineNumber);
            }
        }
        PreviousStatementContainer.setPrevStatement(lineType);
    }

    /**
     * Verify that a value matches a variable type.
     * @param type the variable type
     * @param value the value string
     */
    default void verifyValue(VariableType type, String value, int lineNum)
            throws IncorrectLineException {
        //This method checks if a value matches a variable type
        switch (type){
            case INT:
                try {
                    Integer.parseInt(value);
                    return;
                } catch (NumberFormatException e){
                    break;
                }

            case DOUBLE:
                try {
                    Double.parseDouble(value);
                    return;
                } catch (NumberFormatException e){
                    break;
                }

            case STRING:
                if (value.startsWith("\"") && value.endsWith("\"")){
                    return;
                }
                break;

            case BOOLEAN:
                if (value.equals(Constants.TRUE_KEYWORD) || value.equals(Constants.FALSE_KEYWORD)) {
                    return;
                }
                else {
                    try {
                        Double.parseDouble(value);
                        return;
                    } catch (NumberFormatException e){
                        break;
                    }
                }

            case CHAR:
                if (value.startsWith("'") && value.endsWith("'") && value.length() == 3){
                    return;
                }
                break;

        }

        Pattern pattern = Pattern.compile(RegexConstants.STANDALONE_VAR_NAME_REGEX);
        Matcher matcher = pattern.matcher(value);
        if (matcher.find()) {
            try {
                verifyVarAssignment(type, value, lineNum);
                return;
            } catch (IncorrectLineException e) {
                throw e;
            }
        }
        throw new LanguageRuleException(Constants.TYPE_MISMATCH_ERROR, lineNum);
    }

    default void verifyVarAssignment(VariableType type, String assignerVarName, int lineNum)
            throws IncorrectLineException {
        VariableAttributes newVar = VariableContainer.getVarInScope(assignerVarName);
        if (newVar == null)
        {
            // var to assign undeclared
            throw new LanguageRuleException(Constants.UNDECLARED_VAR_ERROR, lineNum);
        }
        if (newVar.type != type)
        {
            // var to assign of wrong type
            throw new LanguageRuleException(Constants.TYPE_MISMATCH_ERROR, lineNum);
        }
        if (!newVar.hasValue)
        {
            // var to assign uninitialized
            throw new LanguageRuleException(Constants.INVALID_VAR_ASSIGNMENT, lineNum);
        }
    }

    /**
     * Get the variable type from a string.
     * @param type the string
     * @return the variable type
     */
    default VariableType getType(String type){
        return switch (type) {
            case Constants.INT_KEYWORD -> VariableType.INT;
            case Constants.DOUBLE_KEYWORD -> VariableType.DOUBLE;
            case Constants.STRING_KEYWORD -> VariableType.STRING;
            case Constants.BOOLEAN_KEYWORD -> VariableType.BOOLEAN;
            case Constants.CHAR_KEYWORD -> VariableType.CHAR;
            default -> null;
        };
    }
}
