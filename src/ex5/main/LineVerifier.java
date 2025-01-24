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

    private final LinkedList<LineTypeVerifier> verifiers = new LinkedList<>();

    /**
     * Constructor for the LineVerifier class. Adds all the line type verifiers to the list of verifiers.
     * and adds them to the list of verifiers to be used in the verifyLine method.
     */
    public LineVerifier() {
        VarDeclarationLineVerifier varDeclarationLineVerifier = new VarDeclarationLineVerifier();
        verifiers.add(varDeclarationLineVerifier);
        MethodLineVerifier methodLineVerifier = new MethodLineVerifier();
        verifiers.add(methodLineVerifier);
        WhileLineVerifier whileLineVerifier = new WhileLineVerifier();
        verifiers.add(whileLineVerifier);
        IfLineVerifier ifLineVerifier = new IfLineVerifier();
        verifiers.add(ifLineVerifier);
        VarAssignmentLineVerifier varAssignmentLineVerifier = new VarAssignmentLineVerifier();
//        verifiers.add(varAssignmentLineVerifier);
        BracketLineVerifier bracketLineVerifier = new BracketLineVerifier();
        verifiers.add(bracketLineVerifier);
        ReturnLineVerifier returnLineVerifier = new ReturnLineVerifier();
        verifiers.add(returnLineVerifier);
    }

    /**
     * This method verifies that a line is of a valid type.
     * @param line the line to be verified
     * @return true if the line is of a valid type, false otherwise
     */
    public boolean verifyLine(LineNumberTuple line) {
        boolean isVerified = false;
        for(LineTypeVerifier lineTypeVerifier : verifiers){
            if(lineTypeVerifier.verifyLine(line)){
                isVerified = true;
            }
        }
        if(!isVerified){
            //TODO SYNTAX ERROR
            System.err.printf((Constants.SYNTAX_ERROR), line.lineNumber);
            return false;
        }


    // boolean isMethod = line is a method declaration;

        // if ends with '{' : openNewScope(line);

        // check if line is valid by sending it to each line checking class

        // if ends with '}' : closeCurScope(line);

        return true;
    }

//    private void openNewScope(String line, boolean isMethod) {
//
//        // change scopes:
//        // Scope tmpScope = curScope;
//        // curScope = new Scope(parent = tmpScope, isMethod)
//
//        // if this line is a method declaration, search/add method in curScope.parent (AFTER changing scopes)
//        // if this line has vars, add them to curScope
//    }
//
//    private void closeCurScope(String line) {
//
//        // if this line has vars, search/add them in cur scope BEFORE changing scopes! (this happens in calling func)
//
//        // change scopes:
//        curScope = curScope.parent;
//    }
}
