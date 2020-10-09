package simple;

import java.util.ArrayList;
import java.util.Hashtable;

public class SymbolTable {

    private Integer localVarCounter;
    private Hashtable<String, Variable> GlobalVariableTable;
    private Hashtable<String, Triple<ArrayList<Variable>, String, ArrayList<Parameter>>> MethodsTable;

    public SymbolTable() {
        this.localVarCounter = 1;
        this.GlobalVariableTable = new Hashtable<>();
        this.MethodsTable = new Hashtable<>();

    }

    void addGlobalVariable(String variableName, String method, IType type, IExpression value) {
        Variable variable = new Variable(variableName, method, type, value);
        GlobalVariableTable.put(variableName, variable);
    }

    void addMethod(String methodName, String returnType, ArrayList<Parameter> parameters){
        ArrayList<Variable> variables = new ArrayList<>();
        Triple<ArrayList<Variable>, String, ArrayList<Parameter>> methodContent =
                new Triple<>(variables, returnType, parameters);
        MethodsTable.put(methodName, methodContent);
    }

    Variable getGlobalVariable(String name){
        return GlobalVariableTable.get(name);
    }

    Triple<ArrayList<Variable>, String, ArrayList<Parameter>> getMethod(String name){
        return MethodsTable.get(name);
    }

    void addVariableToMethod(String variableName, String method, IType type, IExpression value){
        Triple<ArrayList<Variable>, String, ArrayList<Parameter>> methodToAdd = getMethod(method);
        Variable variable = new Variable(variableName, method, type, value);
        variable.setIndex(this.localVarCounter);
        this.localVarCounter ++;
        methodToAdd.getA().add(variable);
    }

    void addValueToVariable(String variableName, String methodName, IExpression value){
        Triple<ArrayList<Variable>, String, ArrayList<Parameter>> method = getMethod(methodName);
        for (Variable variable: method.getA()) {
            if (variable.getVariableName().equals(variableName)){
                variable.setValue(value);
                return;
            }
        }

        if (GlobalVariableTable.containsKey(variableName)){
            GlobalVariableTable.get(variableName).setValue(value);
            return;
        }

        throw new RuntimeException("No variable '" + variableName + "'");
    }

    Variable getVariable(String variableName, String methodName){
        Triple<ArrayList<Variable>, String, ArrayList<Parameter>> method = getMethod(methodName);
        for (Variable variable: method.getA()) {
            if (variable.getVariableName().equals(variableName)){
                return variable;
            }
        }

        if (GlobalVariableTable.containsKey(variableName)){
            return GlobalVariableTable.get(variableName);
        }

        throw new RuntimeException("No variable '" + variableName + "'");
    }

}
