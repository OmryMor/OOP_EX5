package ex5.a;

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

public class VerifyDocument {

    public static int Verify(String path){
        // take file path read it and add each row to a list
        List<LineNumberTuple> lines = parseFile(path);
        lines = deleteCommentsAndEmptyRows(lines);
        LineVerifier lineVerifier = new LineVerifier();
        if(!checkLineEndings(lines)){
            return Constants.CODE_ILLEGAL;
        }
        for(LineNumberTuple line: lines){
//            LineContent lineContent = IdentifyLine(line.line);
            if(!lineVerifier.verifyLine(line)){
                return Constants.CODE_ILLEGAL;
            }
        }
        return Constants.CODE_LEGAL;
    }

    public static void printLines(List<LineNumberTuple> lines){
        for(LineNumberTuple line: lines){
            System.out.println(line.lineNumber + " " + line.line);
        }
    }

    private static List<LineNumberTuple> parseFile(String path){
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
            e.printStackTrace();
            //TODO Needs to print 2
        }
        return lines;
    }

    private static List<LineNumberTuple> deleteCommentsAndEmptyRows(List<LineNumberTuple> lines){
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

    private static boolean isCodeEndLine(String line){
        Pattern pattern = Pattern.compile(RegexConstants.CODE_ENDLINE_REGEX);
        Matcher matcher = pattern.matcher(line);
        return matcher.find();
    }

    private static boolean checkLineEndings(List<LineNumberTuple> lines){
        for (LineNumberTuple line : lines) {
            if (!isCodeEndLine(line.line)) {
                System.err.printf((Constants.INCORRECT_ENDING_SUFFIX) + "%n", line.lineNumber);
                return false;
            }
        }
        return true;
    }
}
