package simple;



public class IfStatement implements IStatement {
    public IExpression expression;
    public Body ifBody;
    public Body elseBody;


    public IfStatement(IExpression expression, Body ifBody, Body elseBody) {
        this.expression = expression;
        this.ifBody = ifBody;
        this.elseBody = elseBody;
    }


    @Override
    public String toString() {
        return "\nIf_statement{" +
                "expression=" + expression + '\'' +
                "if=" + ifBody +'\''+
                "else=" + elseBody +'\''+
                '}';
    }
}
