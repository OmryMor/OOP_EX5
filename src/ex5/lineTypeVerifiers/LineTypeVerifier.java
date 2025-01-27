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
    boolean verifyLine(LineNumberTuple lineNumberTuple);

    /**
     * Verify that the expressions in the line are valid.
     * @param expressionString the expression string
     * @param lineNumber the line number
     * @param lineType the type of the line
     * @return true if the expressions are valid, false otherwise
     */
    default boolean verifyExpressions(String expressionString, int lineNumber, LineContent lineType){
        if(expressionString == null){
            //TODO no expression in statement
            System.err.printf((Constants.EMPTY_EXPRESSION_ERROR), lineNumber);
            return false;
        }
        Pattern pattern = Pattern.compile(RegexConstants.CONDITION_REGEX);
        Matcher matcher = pattern.matcher(expressionString);
        while (matcher.find()){
            if(matcher.group(singleExpressionGroup).equals(Constants.TRUE_KEYWORD) ||
                    matcher.group(singleExpressionGroup).equals(Constants.FALSE_KEYWORD)){
                continue;
            }
            try {
                Integer.parseInt(matcher.group(singleExpressionGroup));  // Check if it's an integer
                continue;
            } catch (NumberFormatException e) {
                try {
                    Double.parseDouble(matcher.group(singleExpressionGroup));  // Check if it's a double
                    continue;
                } catch (NumberFormatException ex) {
                    // Not a double either, do nothing
                }
            }
            VariableAttributes var = VariableContainer.getVar(matcher.group(singleExpressionGroup));
            if(var == null){
                //TODO UNKOW EXPRESSION ERROR
                System.err.printf((Constants.UNINITIALIZED_VARAIBLE_IN_EXPRESSIOON), lineNumber);

                return false;
            }
            if(var.type != VariableType.BOOLEAN &&
                    var.type != VariableType.DOUBLE &&
                    var.type != VariableType.INT){
                //TODO INVALID EXPRESSION TYPE
                System.err.printf((Constants.ILLEGAL_TYPE_IN_EXPRESSION), lineNumber);
                return false;
            }
            if(!var.hasValue){
                //TODO variable not initialized error
                System.err.printf((Constants.UNINITIALIZED_VARAIBLE_IN_EXPRESSIOON), lineNumber);
                return false;
            }
        }
        PreviousStatementContainer.setPrevStatement(lineType);
        return true;
    }

    /**
     * Verify that a value matches a variable type.
     * @param type the variable type
     * @param value the value string
     * @return true if the value matches the variable type, false otherwise
     */
    default boolean verifyValue(VariableType type, String value){
        //This method checks if a value matches a variable type
        switch (type){
            case INT:
                try {
                    Integer.parseInt(value);
                } catch (NumberFormatException e){
                    return false;
                }
                break;

            case DOUBLE:
                try {
                    Double.parseDouble(value);
                } catch (NumberFormatException e){
                    return false;
                }
                break;

            case STRING:
                if (!value.startsWith("\"") || !value.endsWith("\"")){
                    return false;
                }
                break;

            case BOOLEAN:
                if (!value.equals(Constants.TRUE_KEYWORD) && !value.equals(Constants.FALSE_KEYWORD)){
                    try {
                        Double.parseDouble(value);
                    } catch (NumberFormatException e){
                        return false;
                    }
                }
                break;

            case CHAR:
                if (!value.startsWith("'") || !value.endsWith("'") || value.length() != 3){
                    return false;
                }
                break;

            case VARIABLE:
                VariableAttributes newVar = VariableContainer.getVar(value);
                if(newVar == null || newVar.type != type) return false;
                break;
        }
        return true;
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
