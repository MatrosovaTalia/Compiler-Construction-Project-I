package lexems;

import simple.ILexem;

public class RoutineDeclaration implements ILexem {
    public ILexem identifier;
    public Parameters parameters;
    public Type type = null;
    public Body body;

    public RoutineDeclaration(ILexem identifier, Parameters parameters, Type type, Body body) {
        this.identifier = identifier;
        this.parameters = parameters;
        this.type = type;
        this.body = body;
    }

    public RoutineDeclaration(ILexem identifier, Parameters parameters, Body body) {
        this.identifier = identifier;
        this.parameters = parameters;
        this.body = body;
    }
}
