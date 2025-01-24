package ex5.a.lineTypeVerifiers;

import ex5.utils.LineNumberTuple;

/**
 * This interface is implemented by classes that verify the type of a line in the code.
 * @author Omry Mor, Ruth Schiller
 */
public interface LineTypeVerifier {

    /**
     * Verify that the line is of the correct type.
     * @param lineNumberTuple the line number and the line content
     * @return true if the line is of the correct type, false otherwise
     */
    boolean verifyLine(LineNumberTuple lineNumberTuple);
}
