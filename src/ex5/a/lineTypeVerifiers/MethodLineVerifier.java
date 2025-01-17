package ex5.a.lineTypeVerifiers;

import ex5.utils.LineNumberTuple;
import ex5.a.Containers.Scope;

public class MethodLineVerifier implements LineTypeVerifier{
    @Override
    public boolean verifyLine(Scope scope, LineNumberTuple lineNumberTuple) {
        return true;
    }
}
