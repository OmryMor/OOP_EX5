package ex5.a.Containers;

import ex5.a.LineContent;

public class PreviousStatementContainer {
    private static LineContent prevStatement = LineContent.ILLEGAL;

    public static LineContent getPrevStatement(){
        return prevStatement;
    }

    public static void setPrevStatement(LineContent statement){
        prevStatement = statement;
    }
}
