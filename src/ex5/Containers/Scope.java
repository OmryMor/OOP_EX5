package ex5.Containers;

import java.util.HashMap;

/**
 * This class represents a scope in the script. It holds the variables and methods in the scope and the parent scope.
 * It also holds a boolean value that indicates if the scope is in a method or not.
 * The class has methods to add a variable to the scope and to find a variable in the scope.
 * The class also has methods to add a method to the scope and to find a method in the scope
 * @author Omry Mor, Ruth Schiller
 */
public class Scope {

    /**
     * A hashmap that holds the variables in the scope
     */
    public final HashMap<String, VariableAttributes> vars;

    /**
     * The parent scope
     */
    public final Scope parent;

    /**
     * A boolean value that indicates if the scope is in a method or not
     */
    public final boolean inMethod;

    /**
     * Constructor for the scope. Initializes the variables and methods hashmaps.
     * @param parent the parent scope
     * @param inMethod true if the scope is in a method, false otherwise
     */
    public Scope(Scope parent, boolean inMethod){
        vars = new HashMap<>();
        this.parent = parent;
        this.inMethod = inMethod;
    }

    /**
     * Add a variable to the current scope
     * @param storage the variable to add
     * @return true if the variable was added, false otherwise (if it was already added)
     */
    public boolean AddVarToScope(VariableAttributes storage){
        if (findVarInCurScope(storage.name) != null) return false;
        vars.put(storage.name, storage);
        return true;
    }

    public VariableAttributes findVarInCurScope(String name) {
        if (vars.containsKey(name)) return vars.get(name);
        return null;
    }

    /**
     * Find a variable in the current scope and all parent scopes
     * @param name the name of the variable
     * @return the variable with the given name or null if it doesn't exist
     */
    public VariableAttributes findVarInAllScopes(String name) {
        if (vars.containsKey(name)) return vars.get(name);
        else if (parent == null) return null;
        else return parent.findVarInAllScopes(name);
    }
}
