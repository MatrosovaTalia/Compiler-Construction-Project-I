package lexems;

public class TypeDeclaration implements ILexem {
    public ILexem identifier;
    public Type type;

    public TypeDeclaration(ILexem identifier, Type type){
        this.identifier = identifier;
        this.type = type;
    }
}
