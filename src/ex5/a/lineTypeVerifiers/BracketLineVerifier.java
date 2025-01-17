package ex5.a.lineTypeVerifiers;

import ex5.utils.LineNumberTuple;
import ex5.a.Scope;

public class BracketLineVerifier implements LineTypeVerifier {
    @Override
    public boolean verifyLine(Scope scope, LineNumberTuple lineNumberTuple) {
        return false;
    }
}
