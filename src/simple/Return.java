package simple;


public class Return implements IStatement {
    IExpression expression;

    public Return(IExpression expression) {
        this.expression = expression;
    }

    @Override
    public String toString() {
        return "\nReturn{" +
                "expression=" + expression + '\'' +
                '}';
    }
}
