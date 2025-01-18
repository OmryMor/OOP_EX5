package ex5.a.lineTypeVerifiers;

import ex5.utils.LineNumberTuple;

public class ReturnLineVerifier implements  LineTypeVerifier{
    @Override
    public boolean verifyLine(LineNumberTuple lineNumberTuple) {
        return false;
    }
}
