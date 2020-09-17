package lexems;

public class RoutineCall implements ILexem {
    public Identifier identifier;
    public Expressions expressions;

    public RoutineCall(Identifier identifier, Expressions expressions) {
        this.identifier = identifier;
        this.expressions = expressions;
    }
}
