package ex5.a;

import java.util.HashMap;

public class Scope {

    HashMap<String, VariableAttributes> vars;
    HashMap<String, MethodAttributes> methods;
    Scope parent;
    boolean inMethod;

    Scope(Scope parent, boolean inMethod){
        vars = new HashMap<>();
        methods = new HashMap<>();
        this.parent = parent;
        this.inMethod = inMethod;
    }

    public boolean AddMethodToScope(String name, MethodAttributes storage){
        if (findMethodInScope(name) != null) return false;
        methods.put(name, storage);
        return true;
    }

    public boolean AddVarToScope(String name, VariableAttributes storage){
        if (findVarInScope(name) != null) return false;
        vars.put(name, storage);
        return true;
    }

    public MethodAttributes findMethodInScope(String name){
        if (methods.containsKey(name)) return methods.get(name);
        else if (parent == null) return null;
        else return parent.findMethodInScope(name);
    }

    public VariableAttributes findVarInScope(String name) {
        if (vars.containsKey(name)) return vars.get(name);
        else if (parent == null) return null;
        else return parent.findVarInScope(name);
    }

}
