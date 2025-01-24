package ex5.utils;

/**
 * This class represents a tuple of a line number and a line.
 * @author Omry Mor, Ruth Schiller
 */
public class LineNumberTuple {

    /**
     * The line number.
     */
    public int lineNumber;

    /**
     * The line.
     */
    public String line;

    /**
     * Constructor for the LineNumberTuple class.
     * @param lineNumber the line number
     * @param line the line
     */
    public LineNumberTuple(int lineNumber, String line) {
        this.lineNumber = lineNumber;
        this.line = line;
    }
}

