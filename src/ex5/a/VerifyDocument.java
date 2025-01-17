package ex5.a;

import ex5.utils.RegexConstants;
import ex5.utils.ScopeManager;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class VerifyDocument {

    public static final String INCORRECT_ENDING_SUFFIX = "There is a line that does not end with a semicolon or curly brace (line %d)";


    public static int Verify(String path){
        // take file path read it and add each row to a list
        VariableVerifier variableVerifier = new VariableVerifier();
        List<LineNumberTuple> lines = parseFile(path);
        lines = deleteCommentsAndEmptyRows(lines);
        if(!checkLineEndings(lines)){
            return 1;
        }
        for(LineNumberTuple line: lines){
            LineContent lineContent = IdentifyLine(line.line);
            if(!verifyLine(lineContent, line.line)){
                return 1;
            }
        }
        if(ScopeManager.getScope() != 0){
            return 1;
            //TODO Amount of brackets is not equal-
        }
        return 0;
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
                System.err.printf((INCORRECT_ENDING_SUFFIX) + "%n", line.lineNumber);
                return false;
            }
        }
        return true;
    }

    private static LineContent IdentifyLine(String line){

        Pattern pattern = Pattern.compile(RegexConstants.VARIABLE_DECLARATION_REGEX);
        Matcher matcher = pattern.matcher(line);
        if(matcher.find()) return LineContent.VARIABLE_DECLARATION;

        pattern = Pattern.compile(RegexConstants.METHOD_DECLARATION_REGEX);
        matcher = pattern.matcher(line);
        if(matcher.find()) return LineContent.METHOD_DECLARATION;

        pattern = Pattern.compile(RegexConstants.IF_STATEMENT_REGEX);
        matcher = pattern.matcher(line);
        if(matcher.find()) return LineContent.IF_STATEMENT;

        pattern = Pattern.compile(RegexConstants.WHILE_STATEMENT_REGEX);
        matcher = pattern.matcher(line);
        if (matcher.find()) return LineContent.WHILE_STATEMENT;

        pattern = Pattern.compile(RegexConstants.VAR_ASSIGNMENT_REGEX);
        matcher = pattern.matcher(line);
        if (matcher.find()) return LineContent.VAR_ASSIGNMENT;

        if(line.equals("}")) return LineContent.CLOSE_BRACKET;

        return LineContent.ILLEGAL;
    }

    private static boolean verifyLine(LineContent content, String line){
        switch(content){
            case CLOSE_BRACKET:
                return line.equals("}");
            case ILLEGAL:
                return false;
        }
        return false;
    }

    public static class LineNumberTuple {
        public int lineNumber;
        public String line;
        public LineNumberTuple(int lineNumber, String line) {
            this.lineNumber = lineNumber;
            this.line = line;
        }
    }
}
