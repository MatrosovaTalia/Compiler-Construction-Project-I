package simple.table;

import java.util.ArrayList;

public class Method {
    String methodName;
    String returnType;
    ArrayList<Variable> locals;

    public Method(String methodName, String returnType, ArrayList<Variable> locals) {
        this.methodName = methodName;
        this.returnType = returnType;
        this.locals = locals;
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
        for (int i = 0; i < locals.size(); i++) {
            if (locals.get(i).getVariableName().equals(varName)) {
                return locals.get(i);
            }
        }
        return null;
    }
}
