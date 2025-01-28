package ex5.lineTypeVerifiers;

import ex5.Containers.MethodAttributes;
import ex5.Containers.MethodsContainer;
import ex5.Containers.VariableAttributes;
import ex5.Containers.VariableContainer;
import ex5.main.LineVerifier;
import ex5.utils.Constants;
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
    public boolean verifyLine(LineNumberTuple lineNumberTuple) {
        Pattern pattern = Pattern.compile(RegexConstants.METHOD_CALL_REGEX);
        Matcher matcher = pattern.matcher(lineNumberTuple.line);
        if (!matcher.find()) {
            return false;
        }
        String methodName = matcher.group(methodNameGroup);
        MethodAttributes methodAttributes = MethodsContainer.getMethod(methodName);
        if (methodAttributes == null) {
            //TODO Method does not exist error
            System.err.printf(Constants.METHOD_NOT_DECLARED, lineNumberTuple.lineNumber);
            return false;
        }
        if(!checkMethodParameters(matcher.group(methodParametersGroup), methodAttributes, lineNumberTuple)){
            return false;
        }
        return true;
    }

    private boolean checkMethodParameters(String params, MethodAttributes methodAttributes,
                                          LineNumberTuple lineNumberTuple){
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
                //TODO err - too many parameters given to method
                System.err.printf((Constants.METHOD_PARAMETERS_MISMATCH), lineNumberTuple.lineNumber);
                return false;
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
                //TODO err - parameters given to method dont match methods signature
                System.err.printf((Constants.METHOD_CALL_INVALID), lineNumberTuple.lineNumber);
                return false;
            }
            i++;
        }
        if(i != typeList.size()){
            //TODO err - not enough parameters given to method
            System.err.printf((Constants.METHOD_PARAMETERS_MISMATCH), lineNumberTuple.lineNumber);
            return false;
        }
        return true;
    }
}
