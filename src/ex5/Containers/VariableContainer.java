package ex5.Containers;

/**
 * This class holds all data for all variables in the script.
 * @author Omry Mor, Ruth Schiller
 */
public class VariableContainer {

    private static Scope currentScope = new Scope(null, false);

    /**
     * Enter a deeper scope level
     */
    public static void scopeIn(){
        currentScope = new Scope(currentScope,true);
    }

    /**
     * Check if the current scope is the global scope
     * @return true if the current scope is the global scope, false otherwise
     */
    public static boolean inGlobalScope(){
        return !currentScope.inMethod;
    }

    /**
     * Exit the current scope level
     * @return true if the scope was exited, false if the current scope is the global scope
     */
    public static boolean scopeOut(){
        if(currentScope.parent != null){
            currentScope = currentScope.parent;
            return true;
        }
        return false;
    }

    /**
     * Add a variable to the current scope
     * @param var the variable to add
     * @return true if the variable was added, false otherwise (if it was already added)
     */
    public static boolean addVarToCurrentScope(VariableAttributes var){
        return currentScope.AddVarToScope(var);
    }

    /**
     * Find a variable in the current scope
     * @param name the name of the variable
     * @return the variable with the given name or null if it doesn't exist
     */
    public static VariableAttributes getVar(String name){
        return currentScope.findVarInScope(name);
    }

}
