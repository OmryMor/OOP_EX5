package ex5.a;

public class VariableAttributes {

    public final VariableType type;
    public final float scope;
    public boolean hasValue;
    public boolean isFinal;

    public VariableAttributes(VariableType type, float scope, boolean hasValue, boolean isFinal) {
        this.hasValue = hasValue;
        this.type = type;
        this.scope = scope;
        this.isFinal = isFinal;
    }
}
