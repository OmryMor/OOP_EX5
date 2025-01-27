package ex5.lineTypeVerifiers;

import ex5.Containers.PreviousStatementContainer;
import ex5.Containers.VariableAttributes;
import ex5.Containers.VariableContainer;
import ex5.main.LineVerifier;
import ex5.utils.LineContent;
import ex5.utils.LineNumberTuple;
import ex5.utils.VariableType;
import ex5.utils.Constants;
import ex5.utils.RegexConstants;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This class verifies that a line is a variable declaration line.
 * @author Omry Mor, Ruth Schiller
 */
public class VarDeclarationLineVerifier implements LineTypeVerifier{

    private final int varDefinitionGroup = 3;
    private final int singleDeclarationNameGroup = 1;
    private final int singleDeclarationValueGroup = 6;
    private final int varTypeCaptureGroup = 2;

    /**
     * Verify that the line is a variable declaration line.
     * @param lineNumberTuple the line number and the line content
     * @return true if the line is a variable declaration line, false otherwise
     */
    @Override
    public boolean verifyLine( LineNumberTuple lineNumberTuple) {
        Pattern pattern = Pattern.compile(RegexConstants.VARIABLE_DECLARATION_REGEX);
        Matcher matcher = pattern.matcher(lineNumberTuple.line);
        if (!matcher.find()) {
            return false;
        }
        VariableType type = getType(matcher.group(varTypeCaptureGroup));
        boolean isFinal = lineNumberTuple.line.contains(Constants.FINAL_KEYWORD);

        pattern = Pattern.compile(RegexConstants.SINGLE_DECLARATION_REGEX);
        matcher = pattern.matcher(matcher.group(varDefinitionGroup));

        while (matcher.find()) {
            String name = matcher.group(singleDeclarationNameGroup);
            String value = matcher.group(singleDeclarationValueGroup);
            boolean hasValue = value != null;
            if(!hasValue && isFinal){
                throw new LanguageRuleException(Constants.FINAL_VARIABLE_NOT_INITIALIZED_ERROR,
                        lineNumberTuple.lineNumber);
                // TODO return false;
            }
            if(isSafeWord(name)){
                throw new SyntaxErrorException(Constants.KEYWORD_AS_VARIABLE_ERROR,
                        lineNumberTuple.lineNumber);
                // TODO return false;
            }
            if(hasValue && !verifyValue(type, value)){
                throw new LanguageRuleException(Constants.TYPE_MISMATCH_ERROR, lineNumberTuple.lineNumber);
                // TODO return false;
            }
            if(LineVerifier.isFirstPass){
                VariableAttributes var = new VariableAttributes(type, hasValue, isFinal, name);
                if(!VariableContainer.addVarToCurrentScope(var)){
                    throw new LanguageRuleException(Constants.VAR_NAME_TAKEN_ERROR,
                            lineNumberTuple.lineNumber);
                    // TODO return false;
                }
            }
        }
        PreviousStatementContainer.setPrevStatement(LineContent.VARIABLE_DECLARATION);
        return true;
    }



    private boolean isSafeWord(String word){
        //This method checks if a word is a reserved keyword
        return Arrays.asList(Constants.KEYWORDS).contains(word);
    }
}
