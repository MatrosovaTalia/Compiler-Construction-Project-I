package lexems;

public class Summand implements ILexem {
    public ILexem s;

    public Summand(Primary primary) {
        s = primary;
    }

    public Summand(Expression expression) {
        s = expression;
    }
}
