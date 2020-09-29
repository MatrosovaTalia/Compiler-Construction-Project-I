package simple;


public class ArrayType implements IType {
    IExpression size;
    IType type;

    public ArrayType(IExpression size, IType type){
        this.size = size;
        this.type = type;
    }

    @Override
    public String toString() {
        return "Array_Type {" +
                "Arr_size=" + size + '\'' +
                "Arr_type=" + type + '\'' +
                '}';
    }
}
