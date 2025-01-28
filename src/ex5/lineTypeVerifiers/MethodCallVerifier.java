package ex5.lineTypeVerifiers;

import ex5.Containers.*;
import ex5.utils.Constants;
import ex5.utils.LineContent;
import ex5.utils.LineNumberTuple;
import ex5.utils.RegexConstants;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This class verifies that a line is a method call.
 * @author Omry Mor, Ruth Schiller
 */
public class MethodCallVerifier implements LineTypeVerifier {

    private static final int methodNameGroup = 1;
    private static final int methodParametersGroup = 2;

    /**
     * Verify that the line is a method call.
     * @param lineNumberTuple the line number and the line content
     * @return true if the line is a method call, false otherwise
     */
    @Override
    public boolean verifyLine(LineNumberTuple lineNumberTuple) throws IncorrectLineException {
        Pattern pattern = Pattern.compile(RegexConstants.METHOD_CALL_REGEX);
        Matcher matcher = pattern.matcher(lineNumberTuple.line);
        if (!matcher.find()) {
            return false;
        }
        String methodName = matcher.group(methodNameGroup);
        MethodAttributes methodAttributes = MethodsContainer.getMethod(methodName);
        if (methodAttributes == null) {
            throw new LanguageRuleException(Constants.METHOD_NOT_DECLARED, lineNumberTuple.lineNumber);
        }
        try {
            checkMethodParameters(matcher.group(methodParametersGroup), methodAttributes, lineNumberTuple);
        } catch (IncorrectLineException e) {
            throw e;
        }
        PreviousStatementContainer.setPrevStatement(LineContent.METHOD_CALL);
        return true;
    }

    private void checkMethodParameters(String params, MethodAttributes methodAttributes,
                                          LineNumberTuple lineNumberTuple) throws IncorrectLineException {
        //validates that the given parameters match the method signature
        if(params == null) params = "";
        Pattern pattern = Pattern.compile(RegexConstants.VAR_VALUE_REGEX);
        Matcher matcher = pattern.matcher(params);
        List<VariableAttributes> typeList =  methodAttributes.getVarTypes();
        int i = 0;
        // check method variables
        while (matcher.find()){
            boolean isValidVar = true;
            boolean isValidPrimitive = true;

            if(i == typeList.size()){
                throw new LanguageRuleException(Constants.METHOD_PARAMETERS_MISMATCH,
                        lineNumberTuple.lineNumber);
            }

            String paramString = matcher.group(0);
            VariableAttributes var = VariableContainer.getVar(paramString);
            if(var == null || var.type != typeList.get(i).type || !var.hasValue){
                isValidVar = false;
            }
            try {
                verifyValue(typeList.get(i).type, paramString, lineNumberTuple.lineNumber);
            } catch (IncorrectLineException e){
                isValidPrimitive = false;
            }

            if(!isValidVar && !isValidPrimitive){
                throw new LanguageRuleException(Constants.METHOD_CALL_INVALID, lineNumberTuple.lineNumber);
            }
            i++;
        }
        if(i != typeList.size()){
            throw new LanguageRuleException(Constants.METHOD_PARAMETERS_MISMATCH, lineNumberTuple.lineNumber);
        }
    }
}
