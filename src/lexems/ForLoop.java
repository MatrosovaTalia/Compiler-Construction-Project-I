package lexems;

public class ForLoop implements ILexem{
    public ILexem identifier;
    public Expression expression1;
    public Expression expression2;
    public boolean reverse;
    public Body body;

    public ForLoop (ILexem identifier, Expression expression1, Expression expression2, Body body, boolean r) {
        this.identifier = identifier;
        this.body = body;
        this.expression1 = expression1;
        this.expression2 = expression2;
        this.reverse = r;
    }
}
