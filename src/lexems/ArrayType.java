package lexems;

public class ArrayType implements ILexem {
    public Expression size;
    public Type type;

    public ArrayType(Expression size, Type type) {
        this.size = size;
        this.type = type;
    }
}
