package simple.table;

import java.util.ArrayList;

public class ArrayReferenceVariable extends Variable {

    public String index(String type) {
        if (this.getVariableType().startsWith("[")) {
            return this.getVariableType().substring(1);
        }
        else {
            throw new RuntimeException(
                    String.format("Variable %s of type %s is not indexable!",
                            this.getVariableName(),
                            this.getVariableType()
                    )
            );
        }
    }

    public ArrayReferenceVariable(String variableName, String type) {
        super(variableName, type);
    }

    public ArrayReferenceVariable(String variableName, String type, ArrayList<Integer> dims) {
        super(variableName, type);
    }
}
