package simple;

import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;

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

    @Override
    public void emit(ClassWriter cw, MethodVisitor mv) {

    }

    @Override
    public String resolve() {
        String type;
        switch (primitivetype) {
            case "boolean" -> type = "Z";
            case "real" -> type = "D";
            default -> type = "I";
        }
        return type;
    }
}
