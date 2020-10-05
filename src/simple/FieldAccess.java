package simple;

public class FieldAccess implements IPrimary{
    Identifier id;

    public FieldAccess(Identifier id){
        this.id = id;
    }
    @Override
    public String toString() {
        return "\nField_Access{" +
                "id=" + id + '\'' +
                '}';
    }

}
