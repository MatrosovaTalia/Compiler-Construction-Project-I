package lexems;

public class RoutineDeclaration implements ILexem{
    public Identifier identifier;
    public Parameters parameters;
    public Type type = null;
    public Body body;

    public RoutineDeclaration(Identifier identifier, Parameters parameters, Type type, Body body) {
        this.identifier = identifier;
        this.parameters = parameters;
        this.type = type;
        this.body = body;
    }

    public RoutineDeclaration(Identifier identifier, Parameters parameters, Body body) {
        this.identifier = identifier;
        this.parameters = parameters;
        this.body = body;
    }
}
