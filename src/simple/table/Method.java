package simple.table;

import java.util.ArrayList;

public class Method {
    String methodName;
    public final String descriptor;
    ArrayList<Variable> locals;

    public Method(String methodName, String returnType, ArrayList<Variable> locals) {
        this.methodName = methodName;
        this.descriptor = returnType;
        this.locals = locals;
    }

    public String getReturnType() {
        var lst = descriptor.split("[()]");
        return lst[lst.length-1];
    }


    public int getLocalVariableIndex(String varName) {
        for (int i = 0; i < locals.size(); i++) {
            if (locals.get(i).getVariableName().equals(varName)) {
                return i;
            }
        }
        return -1;
    }

    public Variable getLocalVariable(String varName) {
        for (Variable local : locals) {
            if (local.getVariableName().equals(varName)) {
                return local;
            }
        }
        return null;
    }
}
