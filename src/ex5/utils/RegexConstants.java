package ex5.utils;

public class RegexConstants {
    public static final String COMMENT_REGEX = "^\\s*$|^\\s*//",
            CODE_ENDLINE_REGEX = ".*[{};]\\s*$",

            VAR_TYPE_REGEX = "^(final)?\\s(int|double|String|boolean|char)\\s",
            VAR_NAME_REGEX = "([A-Za-z][A-Za-z0-9_]*|_[A-Za-z0-9][A-Za-z0-9_]*)",
            VAR_VALUE_REGEX = "((\\s*[=]\\s*)([-+]?[\\d]*[.]?[\\d]+|[\"].*[\"]|['].[']|true|false))?(\\s*[,;]\\s*)",
            VARIABLE_DECLARATION_REGEX = String.format("^(%s)((%s)(%s))+$",VAR_TYPE_REGEX, VAR_NAME_REGEX, VAR_VALUE_REGEX),

            CONDITION_REGEX = "true|false|[-+]?[\\d]*[.]?[\\d]+|"+VAR_NAME_REGEX,
            CHAINED_CONDITION_REGEX = String.format("(%s)((\\s*(\\|\\||&&)\\s*(%s))*)", CONDITION_REGEX, CONDITION_REGEX),
            IF_STATEMENT_REGEX = String.format("^\\s*if\\s*(\\s*%s\\s*)\\s*{\\s*$", CHAINED_CONDITION_REGEX),
            WHILE_STATEMENT_REGEX = String.format("^\\s*while\\s*(\\s*%s\\s*)\\s*{\\s*$", CHAINED_CONDITION_REGEX),
            METHOD_DECLARATION_REGEX = "",
            VAR_ASSIGNMENT_REGEX = ""
                    ;

}
