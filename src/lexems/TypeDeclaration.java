package lexems;

public class TypeDeclaration implements ILexem {
    Identifier identifier;
    Type type;

    public TypeDeclaration(Identifier identifier, Type type){
        this.identifier = identifier;
        this.type = type;
    }
}
