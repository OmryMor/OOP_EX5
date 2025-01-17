package ex5.a.Containers;

import ex5.a.VariableType;

public class VariableAttributes {

    public final VariableType type;
    public boolean hasValue;
    public final boolean isFinal;
    public final String name;

    public VariableAttributes(VariableType type, boolean hasValue, boolean isFinal, String name) {
        this.hasValue = hasValue;
        this.type = type;
        this.isFinal = isFinal;
        this.name = name;
    }
}
