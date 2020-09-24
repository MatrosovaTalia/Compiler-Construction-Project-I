package simple;

import lexems.Identifier;

public class VariableDeclaration implements IDeclaration{
    Identifier id;
    IType type;

    public VariableDeclaration(Identifier id, IType type){
        this.id = id;
        this.type = type;
    }
    @Override
    public String toString() {
        return "Variable Declaration {" +
                "id=" + id + '\'' +
                "type=" + type + '\'' +
                '}';
    }

}
