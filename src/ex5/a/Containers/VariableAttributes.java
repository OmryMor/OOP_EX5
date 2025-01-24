package ex5.a.Containers;

import ex5.a.VariableType;

/**
 * This class represents the properties of a variable.
 * @author Omry Mor
 */
public class VariableAttributes {

    /**
     * The type of the variable
     */
    public final VariableType type;
    /**
     * A boolean value that indicates if the variable has a value
     */
    public boolean hasValue;

    /**
     * A boolean value that indicates if the variable is final
     */
    public final boolean isFinal;

    /**
     * The name of the variable
     */
    public final String name;

    /**
     * Constructor
     * @param type the type of the variable
     * @param hasValue true if the variable has a value, false otherwise
     * @param isFinal true if the variable is final, false otherwise
     * @param name the name of the variable
     */
    public VariableAttributes(VariableType type, boolean hasValue, boolean isFinal, String name) {
        this.hasValue = hasValue;
        this.type = type;
        this.isFinal = isFinal;
        this.name = name;
    }
}
