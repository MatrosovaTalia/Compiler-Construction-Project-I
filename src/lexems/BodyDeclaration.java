package lexems;


public class BodyDeclaration implements ILexem {
    public ILexem body;

    public BodyDeclaration(SimpleDeclaration simpleDeclaration) {
        body = simpleDeclaration;
    }

    public BodyDeclaration(Statement statement) {
        body = statement;
    }
}
