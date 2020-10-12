package simple.table;

import simple.IExpression;
import simple.Parameters;

import java.util.ArrayList;
import java.util.Hashtable;

public class SymbolTable {

    private Hashtable<String, Variable> GlobalVariableTable;
    private Hashtable<String, Method> MethodsTable;

    public SymbolTable() {
        this.GlobalVariableTable = new Hashtable<>();
        this.MethodsTable = new Hashtable<>();

    }

    public void addGlobalVariable(String variableName, String type, IExpression value) {
        Variable variable = new Variable(variableName, "global", type, value);
        GlobalVariableTable.put(variableName, variable);
    }

    public void addMethod(String methodName, String returnType, Parameters params){
        ArrayList<Variable> variables = new ArrayList<>();
        Method newMethod = new Method(methodName, returnType, variables);
        MethodsTable.put(methodName, newMethod);
        for (var param: params) {
            addVariableToMethod(param.id.v, methodName, param.type.resolve(), null);
        }
    }

    public Variable getGlobalVariable(String name){
        return GlobalVariableTable.get(name);
    }

    public boolean methodExists(String methodName) {
        return MethodsTable.containsKey(methodName);
    }

    public boolean localVariableExists(String methodName, String varName) {
        return methodExists(methodName) && (getMethod(methodName).getLocalVariableIndex(varName) != -1);
    }

    public boolean globalVariableExists(String varName) {
        return GlobalVariableTable.containsKey(varName);
    }

    public Variable getLocalVariable(String methodName, String varName) {
        var method = MethodsTable.get(methodName);
        return method.getLocalVariable(varName);
    }

    public int getLocalVariableIndex(String methodName, String varName) {
        var method = MethodsTable.get(methodName);
        return method.getLocalVariableIndex(varName);
    }

    public Method getMethod(String name){
        return MethodsTable.get(name);
    }

    public void addVariableToMethod(String variableName, String method, String type, IExpression value){
        Method methodToAdd = getMethod(method);
        Variable variable = new Variable(variableName, method, type, value);
        methodToAdd.locals.add(variable);
    }

    public void removeVariableFromMethod(String variableName, String methodName) {
        var method = getMethod(methodName);
        var index = method.getLocalVariableIndex(variableName);
        if (index != -1) {
            method.locals.remove(index);
        }
    }

    public void addValueToVariable(String variableName, String methodName, IExpression value){
        Method method = getMethod(methodName);
        for (Variable variable: method.locals) {
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

    public Variable getVariable(String variableName, String methodName){
        Method method = getMethod(methodName);
        for (Variable variable: method.locals) {
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
