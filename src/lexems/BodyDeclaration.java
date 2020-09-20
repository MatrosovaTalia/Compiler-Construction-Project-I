package lexems;


public class BodyDeclaration implements ILexem {
    public ILexem body;

    public BodyDeclaration(ILexem simpleDeclaration) {
        body = simpleDeclaration;
    }
}
