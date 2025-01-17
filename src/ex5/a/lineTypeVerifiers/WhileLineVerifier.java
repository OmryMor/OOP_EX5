package ex5.a.lineTypeVerifiers;

import ex5.a.Scope;

public class WhileLineVerifier implements LineTypeVerifier{
    @Override
    public boolean verifyLine(Scope scope, String line) {
        return true;
    }
}
