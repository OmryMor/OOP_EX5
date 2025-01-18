package ex5.a.lineTypeVerifiers;

import ex5.a.Containers.PreviousStatementContainer;
import ex5.a.Containers.VariableAttributes;
import ex5.a.Containers.VariableContainer;
import ex5.a.LineContent;
import ex5.a.VariableType;
import ex5.utils.Constants;
import ex5.utils.LineNumberTuple;
import ex5.a.Containers.Scope;
import ex5.utils.RegexConstants;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WhileLineVerifier implements LineTypeVerifier{

    private final int expressionStringGroup = 1;
    private final int singleExpressionGroup = 0;

    @Override
    public boolean verifyLine(LineNumberTuple lineNumberTuple) {
        Pattern pattern = Pattern.compile(RegexConstants.WHILE_STATEMENT_REGEX);
        Matcher matcher = pattern.matcher(lineNumberTuple.line);
        if (!matcher.find()) {
            return false;
        }
        if(VariableContainer.inGlobalScope()){
            //todo it is being called outside of a function -> error

            System.err.printf((Constants.CALL_NOT_IN_FUNCTION), lineNumberTuple.lineNumber);
            return false;
        }
        VariableContainer.scopeIn();
        String expressionString = matcher.group(expressionStringGroup);
        if(!verifyExpressions(expressionString, lineNumberTuple.lineNumber)){
            return false;
        }
        PreviousStatementContainer.setPrevStatement(LineContent.WHILE_STATEMENT);
        return true;
    }

    private boolean verifyExpressions(String expressionString, int lineNumber){
        if(expressionString == null){
            //TODO no expression in if statement
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
        return true;
    }
}
