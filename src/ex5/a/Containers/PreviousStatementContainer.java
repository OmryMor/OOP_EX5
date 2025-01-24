package ex5.a.Containers;

import ex5.a.LineContent;

/**
 * This class holds the previous statement in the script.
 * @author Omry Mor, Ruth Schiller
 */
public class PreviousStatementContainer {

    private static LineContent prevStatement = LineContent.ILLEGAL;

    /**
     * Get the previous statement in the script
     * @return the previous statement in the script
     */
    public static LineContent getPrevStatement(){
        return prevStatement;
    }

    /**
     * Set the previous statement in the script
     * @param statement the previous statement in the script
     */
    public static void setPrevStatement(LineContent statement){
        prevStatement = statement;
    }
}
