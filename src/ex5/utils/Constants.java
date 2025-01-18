package ex5.utils;

public class Constants {

    public static final int GLOBAL_SCOPE = 0;

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

        public static final int
            CODE_LEGAL = 0,
            CODE_ILLEGAL = 1,
            IO_ERROR = 2
                    ;

    public static final String
            INCORRECT_ENDING_SUFFIX = "There is a line that does not end with a semicolon or curly brace (line %d)\n",
            FINAL_VARIABLE_NOT_INITIALIZED_ERROR = "Final variable must be initialized with value (line %d)\n",
            KEYWORD_AS_VARIABLE_ERROR = "Keywords cant be used as variable names (line %d)\n",
            TYPE_MISMATCH_ERROR = "Given value cant be assigned to variable type (line %d)\n",
            NAME_TAKEN_ERROR = "There already exists a variable with this name at the current scope (line %d)\n",
            METHOD_NAME_EXISTS_ERROR = "There already exists a method with this name (line %d)\n",
            SYNTAX_ERROR = "Line doesnt match required syntax (line %d)\n"
                    ;

}
