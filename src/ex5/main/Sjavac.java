package ex5.main;

import ex5.lineTypeVerifiers.IncorrectLineException;
import ex5.utils.Constants;

import java.io.IOException;

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
        int result = Constants.CODE_LEGAL;
        try {
            validateNumOfArgs(args.length);
            validateFileType(args[0]);
        } catch (IOException e) {
            System.err.println(e.getMessage());
            result = Constants.IO_ERROR;
        }
        if (result != Constants.IO_ERROR) {
            try {
                VerifyDocument.Verify(args[0]);
            } catch (IncorrectLineException e) {
                System.err.println(e.getMessage());
                result = Constants.CODE_ILLEGAL;
            } catch (IOException ioe){
                System.err.println(ioe.getMessage());
                result = Constants.IO_ERROR;
            }
        }
        System.out.println(result);
    }

    private static void validateNumOfArgs(int argsLength) throws IOException {
        if (argsLength != 1)
        {
            throw new IOException(Constants.WRONG_NUM_OF_ARGS);
        }
    }

    private static void validateFileType(String fileName) throws IOException {
        if (!fileName.endsWith(Constants.SJAVA_SUFFIX))
        {
            throw new IOException(Constants.WRONG_FILE_FORMAT);
        }
    }
}