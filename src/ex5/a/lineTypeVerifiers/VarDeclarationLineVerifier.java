package ex5.a.lineTypeVerifiers;

import ex5.a.Scope;

public class VarDeclarationLineVerifier implements LineTypeVerifier{

    @Override
    public boolean verifyLine(Scope scope, String line) {
        return true;
    }


}
