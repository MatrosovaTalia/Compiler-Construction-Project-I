package simple;

import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;

import static org.objectweb.asm.Opcodes.*;


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
    public void emit(ClassWriter cw, MethodVisitor mv, String methodName, int maxDepth) {
        if (!size.resolve_type(methodName).equals("I")) {
            throw new RuntimeException(String.format("Routine %s: indexes of the array can only be integer!",
                    methodName));
        }
        size.emit(cw, mv, methodName, maxDepth);
        type.emit(cw, mv, methodName, maxDepth);
        String arrayType = resolve();
        int depth = maxDepth(arrayType);
        if (depth == maxDepth) {
            if (depth == 1) {
                var desc = arrayType.charAt(1);
                int typecode;
                switch (desc) {
                    case 'F' -> typecode = T_FLOAT;
                    case 'Z' -> typecode = T_BOOLEAN;
                    default -> typecode = T_INT;
                }
                mv.visitVarInsn(NEWARRAY, typecode);
            } else if (depth > 1) {
                mv.visitMultiANewArrayInsn(arrayType, depth);
            }
        }
    }

    public static int maxDepth(String arrayType) {
        return arrayType.length() - arrayType.replace("[", "").length();
    }

    @Override
    public String resolve() {
        return "[" + type.resolve();
    }
}
