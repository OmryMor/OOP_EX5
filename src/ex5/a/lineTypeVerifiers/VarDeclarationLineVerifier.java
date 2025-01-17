package ex5.a.lineTypeVerifiers;

import ex5.a.Containers.VariableAttributes;
import ex5.a.Containers.VariableContainer;
import ex5.utils.LineNumberTuple;
import ex5.a.Containers.Scope;
import ex5.a.VariableType;
import ex5.utils.Constants;
import ex5.utils.RegexConstants;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class VarDeclarationLineVerifier implements LineTypeVerifier{

    @Override
    public boolean verifyLine(Scope scope, LineNumberTuple lineNumberTuple) {
//todo add diff var assignment to variable
        Pattern pattern = Pattern.compile(RegexConstants.VARIABLE_DECLARATION_REGEX);
        Matcher matcher = pattern.matcher(lineNumberTuple.line);
        if (!matcher.find()) {
            return false;
        }
        VariableType type = getType(matcher.group(RegexConstants.VARIABLE_TYPE_CAPTURE_GROUP));
        boolean isFinal = lineNumberTuple.line.contains(Constants.FINAL_KEYWORD);

        pattern = Pattern.compile(RegexConstants.SINGLE_DECLARATION_REGEX);
        matcher = pattern.matcher(lineNumberTuple.line);

        while (matcher.find()) {
            String name = matcher.group(1);
            String value = matcher.group(4);
            boolean hasValue = value != null;
            if(!hasValue && isFinal){
                //TODO ERROR - FINAL VARIABLE MUST BE INITIALIZED (show line)
                return false;
            }
            if(isSafeWord(name)){
                //TODO ERROR - CANNOT USE KEYWORD AS VARIABLE NAME (show line)
                return false;
            }
            if(!verifyValue(type, value)){
                //TODO ERROR - VALUE DOES NOT MATCH VARIABLE TYPE (show line)
                return false;
            }
            VariableAttributes var = new VariableAttributes(type, hasValue, isFinal, name);
            if(!VariableContainer.addVarToCurrentScope(var)){
                //TODO ERROR - CURRENT SCOPE ALREADY HAS PARAMETER WITH IDENTICAL NAME
                return false;
            }
        }
        return true;
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

    private boolean verifyValue(VariableType type, String value){
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

    private boolean isSafeWord(String word){
        return Arrays.asList(Constants.KEYWORDS).contains(word);
    }
}
