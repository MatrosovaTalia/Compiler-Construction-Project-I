package lexems;

public class VariableDeclaration implements ILexem {
    public Identifier identifier;
    public Type type;
    public Expression expression;

    public VariableDeclaration(Identifier identifier, Type type) {
        this.identifier = identifier;
        this.type = type;
    }

    public VariableDeclaration(Identifier identifier, Type type, Expression expression) {
        this.identifier = identifier;
        this.type = type;
        this.expression = expression;
    }

    public VariableDeclaration(Identifier identifier, Expression expression) {
        this.identifier = identifier;
        this.expression = expression;
    }
}
