package simple;


import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;

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

    @Override
    public void emit(ClassWriter cw, MethodVisitor mv, String methodName) {

    }

    @Override
    public String resolve() {
        return "[" + type.resolve();
    }
}
