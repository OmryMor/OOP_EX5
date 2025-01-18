package ex5.a.lineTypeVerifiers;

import ex5.a.Containers.*;
import ex5.a.LineContent;
import ex5.a.VariableType;
import ex5.utils.Constants;
import ex5.utils.LineNumberTuple;
import ex5.utils.RegexConstants;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MethodLineVerifier implements LineTypeVerifier{

    private final int methodNameGroup = 1;
    private final int finalKeywordGroup = 1;
    private final int paramsListGroup = 2;
    private final int varTypeGroup = 2;
    private final int varNameGroup = 3;




    @Override
    public boolean verifyLine(LineNumberTuple lineNumberTuple) {
        Pattern pattern = Pattern.compile(RegexConstants.METHOD_DECLARATION_REGEX);
        Matcher matcher = pattern.matcher(lineNumberTuple.line);
        if (!matcher.find()) {
            return false;
        }

        //get in scope
        VariableContainer.scopeIn();

        String methodName = matcher.group(methodNameGroup);
        List<VariableAttributes> parametersList = getParametersList(matcher.group(paramsListGroup));
        if(parametersList == null){
            //TODO Var with name already exists in scope error
            System.err.printf((Constants.NAME_TAKEN_ERROR), lineNumberTuple.lineNumber);
            return false;
        }
        MethodAttributes method = new MethodAttributes(methodName, parametersList);

        if(!MethodsContainer.AddMethod(method)){
            //TODO Method with name already exists
            System.err.printf((Constants.METHOD_NAME_EXISTS_ERROR), lineNumberTuple.lineNumber);
            return false;
        }
        //TODO - IF I DEFINE VARIABLE IN DECLARATION CAN I DEFINE IT AGAIN IN FUNCTION?
        PreviousStatementContainer.setPrevStatement(LineContent.METHOD_DECLARATION);
        return true;
    }

    private List<VariableAttributes> getParametersList(String parameterString){
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

    private VariableType getType(String type){
        switch (type){
            case Constants.INT_KEYWORD:
                return VariableType.INT;
            case Constants.DOUBLE_KEYWORD:
                return VariableType.DOUBLE;
            case Constants.STRING_KEYWORD:
                return VariableType.STRING;
            case Constants.BOOLEAN_KEYWORD:
                return VariableType.BOOLEAN;
            case Constants.CHAR_KEYWORD:
                return VariableType.CHAR;
            default:
                return null;
        }
    }
}
