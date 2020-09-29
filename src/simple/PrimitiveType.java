package simple;

public class PrimitiveType implements IType {
    String primitivetype;
    public PrimitiveType(String type){
        this.primitivetype = type;
    }

    @Override
    public String toString() {
        return "Primitive_type{" +
                "primitive_type=" + primitivetype + '\'' +
                '}';
    }
}
