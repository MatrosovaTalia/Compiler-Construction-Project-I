package lexems;

public class Parameter implements ILexem {
    public ILexem identifier;
    public Type type;

    public Parameter(ILexem identifier, Type type) {
        this.identifier = identifier;
        this.type = type;
    }
}
