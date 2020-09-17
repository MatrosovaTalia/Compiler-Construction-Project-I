package lexems;

public class Return implements ILexem{
    Expression expression;

    public Return(Expression expression) {
        this.expression = expression;
    }
}
