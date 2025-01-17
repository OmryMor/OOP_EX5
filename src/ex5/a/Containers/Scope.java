package ex5.a.Containers;

import java.util.HashMap;

public class Scope {

    public HashMap<String, VariableAttributes> vars;
//    HashMap<String, MethodAttributes> methods;
    public Scope parent;
    public boolean inMethod;

    public Scope(Scope parent, boolean inMethod){
        vars = new HashMap<>();
//        methods = new HashMap<>();
        this.parent = parent;
        this.inMethod = inMethod;
    }

//    public boolean AddMethodToScope(String name, MethodAttributes storage){
//        if (findMethodInScope(name) != null) return false;
//        methods.put(name, storage);
//        return true;
//    }

    public boolean AddVarToScope(VariableAttributes storage){
        if (findVarInScope(storage.name) != null) return false;
        vars.put(storage.name, storage);
        return true;
    }
//
//    public MethodAttributes findMethodInScope(String name){
//        if (methods.containsKey(name)) return methods.get(name);
//        else if (parent == null) return null;
//        else return parent.findMethodInScope(name);
//    }

    public VariableAttributes findVarInScope(String name) {
        if (vars.containsKey(name)) return vars.get(name);
        else if (parent == null) return null;
        else return parent.findVarInScope(name);
    }
}
