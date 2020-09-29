package simple;

public class WhileStatement implements IStatement{
    IExpression expression;
    Body body;

    public WhileStatement(IExpression expression, Body body){
        this.expression = expression;
        this.body = body;
    }

    @Override
    public String toString() {
        return "\n  While {" +
                "while_exp=" + expression + '\'' +
                "while_body=" + body + '\'' +
                '}';
    }
}
