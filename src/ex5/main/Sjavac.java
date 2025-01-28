package ex5.main;

import ex5.utils.Constants;

/**
 * This class is the main class of the program.
 * It verifies a document and prints the result.
 * The document is verified by the VerifyDocument class.
 * The result is printed to the console.
 * The main method receives the path to the document as an argument.
 * If the document is legal, the method prints "0".
 * If the document is not legal, the method prints "1".
 * If there was an IO error, the method prints "2".
 */
public class Sjavac {

    /**
     * The main method of the program.
     * It verifies a document and prints the result.
     * @param args the path to the document
     */
    public static void main(String[] args) {
        int result;
        if (args.length != 1)
        {
            System.err.println(Constants.WRONG_NUM_OF_ARGS);
            result = Constants.IO_ERROR;
        }
        else if (!args[0].endsWith(".sjava"))
        {
            System.err.println(Constants.WRONG_FILE_FORMAT);
            result = Constants.IO_ERROR;
        }
        else result = VerifyDocument.Verify("src/ex5/Tests/test452.sjava");
        System.out.println(result);
    }
}