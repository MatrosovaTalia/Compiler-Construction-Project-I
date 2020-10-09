package simple;

public class Variable {

    private IType type;
    private IExpression value;
    private Integer index;
    private String methodName;
    private String variableName;

    public Variable(String variableName, String methodName, IType type, IExpression value){
        this.index = 0;
        this.variableName = variableName;
        this.methodName = methodName;
        this.type = type;
        this.value = value;
    }

    public void setIndex(Integer index){
        this.index = index;
    }

    public void setType(IType type){
        this.type = type;
    }

    public void setValue(IExpression value){
        this.value = value;
    }

    public void setMethodName(String methodName){
        this.methodName = methodName;
    }

    public void setVariableName(String variableName){
        this.variableName = variableName;
    }

    public String getVariableName(){
        return this.variableName;
    }

    public IType getVariableType(){
        return this.type;
    }
}
