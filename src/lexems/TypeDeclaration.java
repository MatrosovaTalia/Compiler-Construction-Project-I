package lexems;

public class TypeDeclaration implements ILexem {
    ILexem identifier;
    Type type;

    public TypeDeclaration(ILexem identifier, Type type){
        this.identifier = identifier;
        this.type = type;
    }
}
