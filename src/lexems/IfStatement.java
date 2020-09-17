package lexems;

public class IfStatement implements ILexem{
    public Expression expression;
    public Body body1;
    public Body body2 = null;

    public IfStatement(Expression expression, Body body) {
        this.expression = expression;
        this.body1 = body;
    }

    public IfStatement(Expression expression, Body body1, Body body2) {
        this.expression = expression;
        this.body1 = body1;
        this.body2 = body2;
    }
}
