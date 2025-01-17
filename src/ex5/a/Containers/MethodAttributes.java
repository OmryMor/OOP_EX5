package ex5.a.Containers;

import ex5.a.VariableType;

import java.util.List;

public class MethodAttributes {

    private final String methodName;
    private final int numOfParams;
    private final List<VariableType> vars;

    MethodAttributes(String methodName, List<VariableType> varTypes)
    {
        this.methodName = methodName;
        this.numOfParams = varTypes.size();
        this.vars = varTypes;
    }

    public List<VariableType> getVarTypes(){
        return this.vars;
    }

    public int getNumOfParams(){
        return numOfParams;
    }

    public String getMethodName(){
        return methodName;
    }
}
