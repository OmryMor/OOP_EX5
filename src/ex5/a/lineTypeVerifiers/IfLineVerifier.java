package ex5.a.lineTypeVerifiers;

import ex5.utils.LineNumberTuple;
import ex5.a.Containers.Scope;

public class IfLineVerifier implements LineTypeVerifier{

    @Override
    public boolean verifyLine(LineNumberTuple lineNumberTuple) {
        return true;
    }
}
