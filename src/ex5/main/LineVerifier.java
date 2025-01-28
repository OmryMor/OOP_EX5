package ex5.main;

import ex5.lineTypeVerifiers.*;
import ex5.utils.Constants;
import ex5.utils.LineNumberTuple;

import java.util.LinkedList;

/**
 * This class verifies that a line is of a valid type.
 * @author Omry Mor, Ruth Schiller
 */
public class LineVerifier {

    /**
     * A boolean that indicates if this is the first pass of the verifier.
     */
    public static boolean isFirstPass = true;

    private final LinkedList<LineTypeVerifier> verifiers = new LinkedList<>();
    private final LinkedList<LineTypeVerifier> firstPassVerifiers = new LinkedList<>();

    /**
     * Constructor for the LineVerifier class. Adds all the line type verifiers to the list of verifiers.
     * and adds them to the list of verifiers to be used in the verifyLine method.
     */
    public LineVerifier() {
        VarDeclarationLineVerifier varDeclarationLineVerifier = new VarDeclarationLineVerifier();
        verifiers.add(varDeclarationLineVerifier);
        firstPassVerifiers.add(varDeclarationLineVerifier);
        MethodLineVerifier methodLineVerifier = new MethodLineVerifier();
        verifiers.add(methodLineVerifier);
        firstPassVerifiers.add(methodLineVerifier);
        WhileLineVerifier whileLineVerifier = new WhileLineVerifier();
        verifiers.add(whileLineVerifier);
        firstPassVerifiers.add(whileLineVerifier);
        IfLineVerifier ifLineVerifier = new IfLineVerifier();
        verifiers.add(ifLineVerifier);
        firstPassVerifiers.add(ifLineVerifier);
        VarAssignmentLineVerifier varAssignmentLineVerifier = new VarAssignmentLineVerifier();
        verifiers.add(varAssignmentLineVerifier);
        BracketLineVerifier bracketLineVerifier = new BracketLineVerifier();
        verifiers.add(bracketLineVerifier);
        firstPassVerifiers.add(bracketLineVerifier);
        ReturnLineVerifier returnLineVerifier = new ReturnLineVerifier();
        verifiers.add(returnLineVerifier);
        firstPassVerifiers.add(returnLineVerifier);
        MethodCallVerifier methodCallVerifier = new MethodCallVerifier();
        verifiers.add(methodCallVerifier);
    }

    /**
     * This method verifies that a line is of a valid type.
     * @param line the line to be verified
     * @return true if the line is of a valid type, false otherwise
     */
    public boolean verifyLine(LineNumberTuple line) {

        if(isFirstPass){
            for(LineTypeVerifier lineTypeVerifier : firstPassVerifiers){
                try {
                    lineTypeVerifier.verifyLine(line);
                } catch (IncorrectLineException e) {
                    System.err.println(e.getMessage());
                }
            }
        }
        else{
            boolean isVerified = false;
            for(LineTypeVerifier lineTypeVerifier : verifiers){
                try {
                    if (lineTypeVerifier.verifyLine(line)) isVerified = true;
                } catch (IncorrectLineException e) {
                    System.err.println(e.getMessage());
                }
            }
            if(!isVerified){
                throw new SyntaxErrorException(Constants.SYNTAX_ERROR, line.lineNumber);
                // TODO return false;
            }
        }
        return true;
    }
}
