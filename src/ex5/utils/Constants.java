package ex5.utils;

/**
 * This class contains all the constants used in the program.
 * @author Omry Mor, Ruth Schiller
 */
public class Constants {

    /**
     * Constants for the different types of variables that can be declared.
     */
    public static final String
        INT_KEYWORD = "int",
        DOUBLE_KEYWORD = "double",
        STRING_KEYWORD = "String",
        CHAR_KEYWORD = "char",
        BOOLEAN_KEYWORD = "boolean",
        FINAL_KEYWORD = "final",
        IF_KEYWORD = "if",
        WHILE_KEYWORD = "while",
        RETURN_KEYWORD = "return",
        TRUE_KEYWORD = "true",
        FALSE_KEYWORD = "false",
        VOID_KEYWORD = "void"
                ;

    /**
     * Array of all the keywords in the language.
     */
    public static final String[] KEYWORDS = {
        INT_KEYWORD,
        DOUBLE_KEYWORD,
        STRING_KEYWORD,
        CHAR_KEYWORD,
        BOOLEAN_KEYWORD,
        FINAL_KEYWORD,
        IF_KEYWORD,
        WHILE_KEYWORD,
        RETURN_KEYWORD,
        TRUE_KEYWORD,
        FALSE_KEYWORD,
        VOID_KEYWORD
    };

    /**
     * Constants for the different types of output codes.
     */
    public static final int
        CODE_LEGAL = 0,
        CODE_ILLEGAL = 1,
        IO_ERROR = 2
                ;

    /**
     * Constants for the different types of errors.
     */
    public static final String
        INCORRECT_ENDING_SUFFIX = "There is a line that does not end with a semicolon or curly brace (line %d)\n",
        KEYWORD_AS_VARIABLE_ERROR = "Keywords cant be used as variable names (line %d)\n",
        SYNTAX_ERROR = "Line doesn't match required syntax (line %d)\n",
        EMPTY_EXPRESSION_ERROR = "No condition in statement (line %d)\n",
        ILLEGAL_SCOPE_ERROR = "Can't use close brackets, already in the global scope (line %d)\n",
        TYPE_MISMATCH_ERROR = "Given value cant be assigned to variable type (line %d)\n",
        UNDECLARED_VAR_ERROR = "Can't assign value to undeclared var (line %d)\n",
        FINAL_VAR_ASSIGN_ERROR = "Can't assign new value to a final variable (line %d)\n",
        FINAL_VARIABLE_NOT_INITIALIZED_ERROR = "Final variable must be initialized with value (line %d)\n",
        UNINITIALIZED_VARAIBLE_IN_EXPRESSIOON = "Variable in expression has not been initialized (line %d)\n",
        ILLEGAL_TYPE_IN_EXPRESSION = "Variable in expression is not double, int or boolean (line %d)\n",
        VAR_NAME_TAKEN_ERROR = "There already exists a variable with this name at the current scope (line %d)\n",
        METHOD_NAME_EXISTS_ERROR = "There already exists a method with this name (line %d)\n",
        METHOD_NOT_ENDING_WITH_RETURN_ERROR = "Method did not end with return statement (line %d)\n",
        CALL_NOT_IN_FUNCTION = "Statement can't be called outside a method body (line %d)\n"
                ;

}
