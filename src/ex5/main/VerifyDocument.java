package ex5.main;

import ex5.Containers.VariableContainer;
import ex5.lineTypeVerifiers.IncorrectLineException;
import ex5.lineTypeVerifiers.LanguageRuleException;
import ex5.lineTypeVerifiers.SyntaxErrorException;
import ex5.utils.Constants;
import ex5.utils.LineNumberTuple;
import ex5.utils.RegexConstants;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This class verifies that a document is legal.
 * It checks that the document is not empty, that it does not contain comments, and that each line is legal.
 * It returns a code indicating whether the document is legal or not.
 * The code is 0 if the document is legal, and 1 if it is not. (2 for IO errors)
 * If the document is not legal, the method prints the line number of the first illegal line.
 * @author Omry Mor, Ruth Schiller
 */
public class VerifyDocument {

    /**
     * Verify that a document is legal.
     * @param path the path to the document
     */
    public static void Verify(String path) throws IncorrectLineException, IOException {
        // parse file and extract lines
        List<LineNumberTuple> lines;
        try{
            lines = parseFile(path);
        } catch (IOException ioe) {
            throw ioe;
        }

        lines = deleteCommentsAndEmptyRows(lines);

        LineVerifier lineVerifier = new LineVerifier();

        try {
            checkAllLineEndingsLegal(lines);
            LineVerifier.isFirstPass = true;
            for (LineNumberTuple line : lines) {
                lineVerifier.verifyLine(line);
            }
            LineVerifier.isFirstPass = false;
            for (LineNumberTuple line : lines) {
                lineVerifier.verifyLine(line);
            }
            if (!VariableContainer.inGlobalScope()) {
                throw new LanguageRuleException(Constants.BRACKETS_MISMATCH, lines.size());
            }
        } catch (IncorrectLineException le){
            throw le;
        }
    }

    /**
     * Print the lines of a document and their line numbers.
     * @param lines the lines to print
     */
    public static void printLines(List<LineNumberTuple> lines){
        for(LineNumberTuple line: lines){
            System.out.println(line.lineNumber + " " + line.line);
        }
    }

    private static List<LineNumberTuple> parseFile(String path) throws IOException{
        // read file and add each row to a list
        List<LineNumberTuple> lines = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            String line;
            int index = 1;
            while ((line = reader.readLine()) != null) {
                LineNumberTuple lineNumberTuple = new LineNumberTuple(index, line.trim());
                lines.add(lineNumberTuple);
                index++;
            }
        } catch (IOException e) {
            throw new IOException(Constants.INVALID_FILE_NAME);
        }
        return lines;
    }

    private static List<LineNumberTuple> deleteCommentsAndEmptyRows(List<LineNumberTuple> lines){
        // delete comments and empty rows
        Pattern pattern = Pattern.compile(RegexConstants.COMMENT_REGEX);
        List<LineNumberTuple> linesWithoutComments = new ArrayList<>();
        for (LineNumberTuple line : lines) {
            Matcher matcher = pattern.matcher(line.line);

            if (!matcher.find()) {
                linesWithoutComments.add(line);
            }
        }
        printLines(linesWithoutComments);
        return linesWithoutComments;
    }

    private static boolean isLineEndLegal(String line){
        // Check if the line ends with a legal end
        Pattern pattern = Pattern.compile(RegexConstants.CODE_ENDLINE_REGEX);
        Matcher matcher = pattern.matcher(line);
        return matcher.find();
    }

    private static void checkAllLineEndingsLegal(List<LineNumberTuple> lines)
            throws IncorrectLineException {
        // Check that each line ends with a semicolon
        for (LineNumberTuple line : lines) {
            if (!isLineEndLegal(line.line)) {
                throw new SyntaxErrorException(Constants.ILLEGAL_LINE_END_SYNTAX, line.lineNumber);
            }
        }
    }
}
