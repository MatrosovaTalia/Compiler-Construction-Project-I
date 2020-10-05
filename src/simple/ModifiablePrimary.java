package simple;

public class ModifiablePrimary implements IExpression{
    Identifier id;
    ElementCall callList;

    public ModifiablePrimary(Identifier id, ElementCall callList){
        this.id = id;
        this.callList = callList;
    }

    @Override
    public String toString() {
        return "\nModifiablePrimary{" +
                "id=" + id + '\'' +
                "Element_calls" + callList + '\'' +
                '}';
    }
}
