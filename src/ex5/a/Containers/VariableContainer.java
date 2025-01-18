package ex5.a.Containers;

public class VariableContainer {
    private static Scope currentScope = new Scope(null, false);

    public static void scopeIn(){
        Scope newScope = new Scope(currentScope,true);
        currentScope = newScope;
    }

    public static boolean inGlobalScope(){
        return !currentScope.inMethod;
    }

    public static boolean scopeOut(){
        if(currentScope.parent != null){
            currentScope = currentScope.parent;
            return true;
        }
        return false;
    }

    public static boolean addVarToCurrentScope(VariableAttributes var){
        return currentScope.AddVarToScope(var);
    }

    public static VariableAttributes getVar(String name){
        return currentScope.findVarInScope(name);
    }
}
