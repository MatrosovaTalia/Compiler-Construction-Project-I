package lexems;

public class RoutineCall implements ILexem {
    public ILexem identifier;
    public Expressions expressions;

    public RoutineCall(ILexem identifier, Expressions expressions) {
        this.identifier = identifier;
        this.expressions = expressions;
    }
}
