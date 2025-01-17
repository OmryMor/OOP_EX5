package ex5.a;

import ex5.a.Containers.Scope;
import ex5.a.lineTypeVerifiers.LineTypeVerifier;

import java.util.LinkedList;

public class LineVerifier {

    private LinkedList<Scope> scopes;
    private LinkedList<LineTypeVerifier> verifiers;
    private Scope curScope;

    LineVerifier() {
        verifiers = new LinkedList<>();
//        curScope = new Scope(null, false);
    }

    public boolean VerifyNextLine(String line) {

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
