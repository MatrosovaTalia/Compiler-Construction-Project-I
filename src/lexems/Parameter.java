package lexems;

public class Parameter implements ILexem {
    public Identifier identifier;
    public Type type;

    public Parameter(Identifier identifier, Type type) {
        this.identifier = identifier;
        this.type = type;
    }
}
