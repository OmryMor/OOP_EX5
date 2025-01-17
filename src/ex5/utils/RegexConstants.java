package ex5.utils;

public class RegexConstants {
    public static final String COMMENT_REGEX = "^\\s*$|^\\s*//",
            CODE_ENDLINE_REGEX = ".*[{};]\\s*$",
            TYPE_REGEX = "int|double|String|boolean|char",
            VAR_TYPE_REGEX = String.format("^(final\\s+)?(%s)\\s+", TYPE_REGEX),
            VAR_NAME_REGEX = "([A-Za-z][A-Za-z0-9_]*|_[A-Za-z0-9][A-Za-z0-9_]*)",
            VAR_VALUE_REGEX = "((\\s*[=]\\s*)([-+]?[\\d]*[.]?[\\d]+|[\"].*[\"]|['].[']|true|false))?",
            SINGLE_DECLARATION_REGEX = String.format("(%s)(%s)", VAR_NAME_REGEX, VAR_VALUE_REGEX),
            VARIABLE_DECLARATION_REGEX = String.format("(%s)(%s,\\s*)*%s;$",VAR_TYPE_REGEX, SINGLE_DECLARATION_REGEX, SINGLE_DECLARATION_REGEX),

            CONDITION_REGEX = "true|false|[-+]?[\\d]*[.]?[\\d]+|"+VAR_NAME_REGEX,
            CHAINED_CONDITION_REGEX = String.format("(%s)((\\s*(\\|\\||&&)\\s*(%s))*)", CONDITION_REGEX, CONDITION_REGEX),
            IF_STATEMENT_REGEX = String.format("^\\s*if\\s*\\(\\s*%s\\s*\\)\\s*{\\s*$", CHAINED_CONDITION_REGEX),
            WHILE_STATEMENT_REGEX = String.format("^\\s*while\\s*\\(\\s*%s\\s*\\)\\s*{\\s*$", CHAINED_CONDITION_REGEX),

            METHOD_NAME_REGEX = "([A-Za-z][A-Za-z0-9_]*)",
            METHOD_PARAMETERS_REGEX = String.format("(%s\\s+%s\\s*,)*\\s*%s\\s+%s\\s*", TYPE_REGEX, VAR_NAME_REGEX, TYPE_REGEX, VAR_NAME_REGEX),
            METHOD_DECLARATION_REGEX = String.format("^void\\s+%s\\s*\\((\\s*%s+\\s*)?\\)\\s*{$", METHOD_NAME_REGEX, METHOD_PARAMETERS_REGEX),
            VAR_ASSIGNMENT_REGEX = "",
            METHOD_CALL_REGEX = "",

            RETURN_REGEX = "^\\s*return\\s*;\\s*$"
                    ;

    public static final int
        VARIABLE_TYPE_CAPTURE_GROUP = 2;
}
