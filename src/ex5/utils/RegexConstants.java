package ex5.utils;

/**
 * This class contains the regexes used to parse the program.
 */
public class RegexConstants {

    /**
     * The regexes used throughout the program.
     */
    public static final String

            COMMENT_REGEX = "^\\s*$|^\\s*//",

            CODE_ENDLINE_REGEX = ".*[{};]\\s*$",

            TYPE_REGEX = "int|double|String|boolean|char",

            VAR_TYPE_REGEX = String.format("(final\\s+)?(%s)\\s+", TYPE_REGEX),

            VAR_NAME_REGEX = "([A-Za-z][A-Za-z0-9_]*|_[A-Za-z0-9][A-Za-z0-9_]*)",

            STANDALONE_VAR_NAME_REGEX = "^\\s*" + VAR_NAME_REGEX + "\\s*$",

            VAR_VALUE_REGEX = "([-+]?[\\d]*[.]?[\\d]+|[\"].*[\"]|['].[']|true|false|" +
                    VAR_NAME_REGEX+")",

            VAR_VALUE_ASSIGNMENT_REGEX = "((\\s*[=]\\s*)"+VAR_VALUE_REGEX + ")?",

            SINGLE_DECLARATION_REGEX = String.format("(%s)(%s)", VAR_NAME_REGEX, VAR_VALUE_ASSIGNMENT_REGEX),

            VARIABLE_DECLARATION_REGEX = "^(final\\s+)?(int|double|String|boolean|char)\\s+((([A-Za-z]" +
                    "[A-Za-z0-9_]*|_[A-Za-z0-9][A-Za-z0-9_]*)((\\s*[=]\\s*)([-+]?[\\d]*[.]?[\\d]+|\".*\"" +
                    "|'.'|true|false|([A-Za-z][A-Za-z0-9_]*|_[A-Za-z0-9][A-Za-z0-9_]*)))?\\s*,\\s*)*([A-Za-z]" +
                    "[A-Za-z0-9_]*|_[A-Za-z0-9][A-Za-z0-9_]*)((\\s*[=]\\s*)([-+]?[\\d]*[.]?[\\d]+|\".*\"|'.'|" +
                    "true|false|([A-Za-z][A-Za-z0-9_]*|_[A-Za-z0-9][A-Za-z0-9_]*)))?)\\s*;",

            CONDITION_REGEX = "true|false|[-+]?[\\d]*[.]?[\\d]+|" + VAR_NAME_REGEX,

            CHAINED_CONDITION_REGEX = String.format("(%s)((\\s*(\\|\\||&&)\\s*(%s))*)",
                    CONDITION_REGEX, CONDITION_REGEX),

            IF_STATEMENT_REGEX = String.format("^\\s*if\\s*\\((\\s*%s\\s*)\\)\\s*\\{\\s*$",
                    CHAINED_CONDITION_REGEX),

            WHILE_STATEMENT_REGEX = String.format("^\\s*while\\s*\\((\\s*%s\\s*)\\)\\s*\\{\\s*$",
                    CHAINED_CONDITION_REGEX),

            METHOD_NAME_REGEX = "([A-Za-z][A-Za-z0-9_]*)",

            METHOD_PARAMETERS_REGEX = String.format("((%s%s\\s*,\\s*)*%s%s\\s*)", VAR_TYPE_REGEX,
                    VAR_NAME_REGEX, VAR_TYPE_REGEX, VAR_NAME_REGEX),

            SINGLE_METHOD_PARAMETER_REGEX = VAR_TYPE_REGEX + VAR_NAME_REGEX,

            METHOD_DECLARATION_REGEX = String.format("^void\\s+%s\\s*\\(((\\s*%s+\\s*))?\\)\\s*\\{$",
                    METHOD_NAME_REGEX, METHOD_PARAMETERS_REGEX),

            VAR_ASSIGNMENT_REGEX = "^\\s*" +
                    "((([A-Za-z][A-Za-z0-9_]*|_[A-Za-z0-9][A-Za-z0-9_]*)" +
                    "((\\s*[=]\\s*)" +
                    "([-+]?[\\d]*[.]?[\\d]+|\".*\"|'.'|true|false|" +
                    "([A-Za-z][A-Za-z0-9_]*|_[A-Za-z0-9][A-Za-z0-9_]*)))" +
                    "\\s*,\\s*)" +
                    "*" +
                    "([A-Za-z][A-Za-z0-9_]*|_[A-Za-z0-9][A-Za-z0-9_]*)" +
                    "((\\s*[=]\\s*)" +
                    "([-+]?[\\d]*[.]?[\\d]+|\".*\"|'.'|true|false|" +
                    "([A-Za-z][A-Za-z0-9_]*|_[A-Za-z0-9][A-Za-z0-9_]*))))" +
                    "\\s*;", // TODO no $?

            SINGLE_VAR_ASSIGN_REGEX = "\\s*" +
                    "(([A-Za-z][A-Za-z0-9_]*|_[A-Za-z0-9][A-Za-z0-9_]*)" +
                    "((\\s*[=]\\s*)" +
                    "([-+]?[\\d]*[.]?[\\d]+|\".*\"|'.'|true|false|" +
                    "([A-Za-z][A-Za-z0-9_]*|_[A-Za-z0-9][A-Za-z0-9_]*)))\\s*)",

            METHOD_CALL_REGEX ="^\\s*([A-Za-z][A-Za-z0-9_]*)\\s*" +
                    "\\(\\s*(([-+]?[\\d]*[.]?[\\d]+|[\"].*[\"]|['].[']|true|false|" +
                    "([A-Za-z][A-Za-z0-9_]*|_[A-Za-z0-9][A-Za-z0-9_]*))(\\s*,\\s*" +
                    "([-+]?[\\d]*[.]?[\\d]+|[\"].*[\"]|['].[']|true|false|([A-Za-z][A-Za-z0-9_]*|" +
                    "_[A-Za-z0-9][A-Za-z0-9_]*)))*\\s*)?\\s*\\)\\s*;\\s*$",

            RETURN_REGEX = "^\\s*return\\s*;\\s*$"
                    ;

}
