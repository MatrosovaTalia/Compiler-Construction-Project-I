package simple;

public class Parameter implements ILexem {
    Identifier id;
    IType type;

    public Parameter(Identifier id, IType type){
        this.id = id;
        this.type = type;
    }

    @Override
    public String toString() {
        return "\nParameter{" +
                "id=" + id + '\'' +
                "type=" + type +'\''+
                '}';
    }

}
