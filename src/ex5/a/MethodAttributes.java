package ex5.a;

import java.util.List;

public class MethodAttributes {

    private int numOfParams;
    private List<VariableType> vars;

    MethodAttributes(int numOfParams, List<VariableType> varTypes)
    {
        this.numOfParams = numOfParams;
        this.vars = varTypes;
    }
}
