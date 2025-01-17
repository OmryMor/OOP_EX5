package ex5.a.lineTypeVerifiers;

import ex5.utils.LineNumberTuple;
import ex5.a.Scope;

public interface LineTypeVerifier {

    boolean verifyLine(Scope scope, LineNumberTuple lineNumberTuple);
}
