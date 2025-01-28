package ex5.utils;

/**
 * This enum represents the different types of content that a line can have.
 */
public enum LineContent {
    /**
     * Close bracket symbol
     */
    CLOSE_BRACKET,
    /**
     * Method declaration line
     */
    METHOD_DECLARATION,
    /**
     * Variable declaration line
     */
    VARIABLE_DECLARATION,
    /**
     * If statement line
     */
    IF_STATEMENT,
    /**
     * While statement line
     */
    WHILE_STATEMENT,
    /**
     * Variable assignment line
     */
    VAR_ASSIGNMENT,
    /**
     * Return statement line
     */
    RETURN,
    /**
     * Method call line
     */
    METHOD_CALL,
    /**
     * Illegal line
     */
    ILLEGAL
}
