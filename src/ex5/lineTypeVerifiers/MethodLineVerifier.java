package ex5.lineTypeVerifiers;

import ex5.Containers.*;
import ex5.main.LineVerifier;
import ex5.utils.LineContent;
import ex5.utils.VariableType;
import ex5.utils.Constants;
import ex5.utils.LineNumberTuple;
import ex5.utils.RegexConstants;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This class verifies that a line is a method declaration.
 * @author Omry Mor, Ruth Schiller
 */
public class MethodLineVerifier implements LineTypeVerifier{

    private final int methodNameGroup = 1;
    private final int finalKeywordGroup = 1;
    private final int paramsListGroup = 2;
    private final int varTypeGroup = 2;
    private final int varNameGroup = 3;

    /**
     * Verify that the line is a method declaration.
     * @param lineNumberTuple the line number and the line content
     * @return true if the line is a method declaration, false otherwise
     */
    @Override
    public boolean verifyLine(LineNumberTuple lineNumberTuple) {
        Pattern pattern = Pattern.compile(RegexConstants.METHOD_DECLARATION_REGEX);
        Matcher matcher = pattern.matcher(lineNumberTuple.line);
        if (!matcher.find()) {
            return false;
        }
        //get in scope
        if(!LineVerifier.isFirstPass)VariableContainer.scopeIn();

        String methodName = matcher.group(methodNameGroup);
        List<VariableAttributes> parametersList = getParametersList(matcher.group(paramsListGroup));
        if(parametersList == null){
            throw new LanguageRuleException(Constants.METHOD_NAME_EXISTS_ERROR, lineNumberTuple.lineNumber);
            // TODO return false;
        }
        if(LineVerifier.isFirstPass){
            MethodAttributes method = new MethodAttributes(methodName, parametersList);
            if(!MethodsContainer.AddMethod(method)){
                throw new LanguageRuleException(Constants.METHOD_NAME_EXISTS_ERROR,
                        lineNumberTuple.lineNumber);
                // TODO return false;
            }
        }
        PreviousStatementContainer.setPrevStatement(LineContent.METHOD_DECLARATION);
        return true;
    }

    private List<VariableAttributes> getParametersList(String parameterString){
        // This method receives a string of parameters and returns a list of VariableAttributes
        List<VariableAttributes> parameterList = new ArrayList<>();
        if(parameterString == null) parameterString = "";
        Pattern pattern = Pattern.compile(RegexConstants.SINGLE_METHOD_PARAMETER_REGEX);
        Matcher matcher = pattern.matcher(parameterString);
        while (matcher.find()){
            boolean isFinal = matcher.group(finalKeywordGroup) != null &&
                    matcher.group(finalKeywordGroup).trim().equals(Constants.FINAL_KEYWORD);
            VariableType varType = getType(matcher.group(varTypeGroup));
            String name = matcher.group(varNameGroup);
            VariableAttributes var = new VariableAttributes(varType, true, isFinal, name);
            if(!VariableContainer.addVarToCurrentScope(var)){
                return null;
            }
            parameterList.add(var);
        }
        return parameterList;
    }
}
