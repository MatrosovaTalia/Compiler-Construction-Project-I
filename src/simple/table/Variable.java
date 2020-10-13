package simple.table;

public class Variable {

    private final String type;
    private final String variableName;

    public Variable(String variableName, String type){
        this.variableName = variableName;
        this.type = type;
    }

    public String getVariableName(){
        return this.variableName;
    }

    public String getVariableType(){
        return this.type;
    }

    @Override
    public String toString() {
        return String.format("Variable %s of type %s\n", variableName, type);
    }

}
