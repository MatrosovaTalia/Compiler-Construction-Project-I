package simple;

public class VariableDeclaration implements IDeclaration{
    Identifier id;
    IType type;
    IExpression value;

    public VariableDeclaration(Identifier id, IType type, IExpression value){
        this.id = id;
        this.type = type;
        this.value = value;
    }

    @Override
    public String toString() {
        return "\n  Variable Declaration {" +
                "Var_id=" + id + '\'' +
                "Var_type=" + type + '\'' +
                "Var_value=" + value + '\'' +
                '}';
    }

}
