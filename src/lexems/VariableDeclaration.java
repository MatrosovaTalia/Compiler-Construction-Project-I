package lexems;

public class VariableDeclaration implements ILexem {
    public ILexem identifier;
    public ILexem type;
    public ILexem expression;

    public VariableDeclaration(ILexem identifier, Type type) {
        this.identifier = identifier;
        this.type = type;
    }

    public VariableDeclaration(ILexem identifier, Type type, ILexem expression) {
        this.identifier = identifier;
        this.type = type;
        this.expression = expression;
    }

    public VariableDeclaration(ILexem identifier, Expression expression) {
        this.identifier = identifier;
        this.expression = expression;
    }
}
