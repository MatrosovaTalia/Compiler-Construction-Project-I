package lexems;

import simple.ILexem;

public class SimpleDeclaration implements ILexem {
    public ILexem simpleDeclaration;

    public SimpleDeclaration(VariableDeclaration variableDeclaration) {
        simpleDeclaration = variableDeclaration;
    }

    public SimpleDeclaration(TypeDeclaration typeDeclaration) {
        simpleDeclaration = typeDeclaration;
    }
}
