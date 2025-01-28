package ex5.Containers;

import java.util.List;

/**
 * This class represents the properties of a method.
 * @author Omry Mor, Ruth Schiller
 */
public class MethodAttributes {

    private final String methodName;
    private final List<VariableAttributes> vars;

    /**
     * Constructor
     * @param methodName the name of the method
     * @param varTypes the types of the variables in the method
     */
    public MethodAttributes(String methodName, List<VariableAttributes> varTypes)
    {
        this.methodName = methodName;
        this.vars = varTypes;
    }

    /**
     * Get the types of the variables in the method
     * @return the types of the variables in the method
     */
    public List<VariableAttributes> getVarTypes(){
        return this.vars;
    }

    /**
     * Get the name of the method
     * @return the name of the method
     */
    public String getMethodName(){
        return methodName;
    }
}
