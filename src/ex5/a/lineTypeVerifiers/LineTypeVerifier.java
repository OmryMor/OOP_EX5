package ex5.a.lineTypeVerifiers;

import ex5.utils.LineNumberTuple;
import ex5.a.Containers.Scope;

public interface LineTypeVerifier {

    boolean verifyLine(Scope scope, LineNumberTuple lineNumberTuple);
}
