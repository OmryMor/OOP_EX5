package ex5.lineTypeVerifiers;

import ex5.Containers.MethodAttributes;
import ex5.Containers.MethodsContainer;
import ex5.Containers.VariableAttributes;
import ex5.Containers.VariableContainer;
import ex5.utils.Constants;
import ex5.utils.LineNumberTuple;
import ex5.utils.RegexConstants;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MethodCallVerifier implements LineTypeVerifier {

    /**
     * Verify that the line is a method call.
     * @param lineNumberTuple the line number and the line content
     * @return true if the line is a method call, false otherwise
     */
    @Override
    public boolean verifyLine(LineNumberTuple lineNumberTuple) {
        Pattern pattern = Pattern.compile(RegexConstants.METHOD_CALL_REGEX);
        Matcher matcher = pattern.matcher(lineNumberTuple.line);
        if (!matcher.find()) {
            return false;
        }
        String methodName = matcher.group(1);
        MethodAttributes methodAttributes = MethodsContainer.getMethod(methodName);
        if (methodAttributes == null) {
            throw new LanguageRuleException(Constants.METHOD_NOT_DECLARED, lineNumberTuple.lineNumber);
            // TODO return false;
        }
        try {
            checkMethodParameters(matcher.group(2), methodAttributes, lineNumberTuple);
        } catch (IncorrectLineException e) {
            throw new RuntimeException(e);
        }
        // TODO return false;
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
                // TODO return false;
            }
            String paramString = matcher.group(0);
            VariableAttributes var = VariableContainer.getVar(paramString);
            if(var == null || var.type != typeList.get(i).type || !var.hasValue){
                isValidVar = false;
            }
            if(!verifyValue(typeList.get(i).type, paramString)){
                isValidPrimitive = false;
            }

            if(!isValidVar && !isValidPrimitive){
                throw new LanguageRuleException(Constants.METHOD_CALL_INVALID, lineNumberTuple.lineNumber);
                // TODO return false;
            }
            i++;
        }
        if(i != typeList.size()){
            throw new LanguageRuleException(Constants.METHOD_PARAMETERS_MISMATCH, lineNumberTuple.lineNumber);
            // TODO return false;
        }
        // TODO return true;
    }
}
