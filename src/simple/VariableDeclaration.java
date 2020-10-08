package simple;

import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes.*;

import static org.objectweb.asm.Opcodes.*;

public class VariableDeclaration implements IDeclaration{
    Identifier id;
    IType type;
    IExpression value;
    static int index = 1;

    public VariableDeclaration(Identifier id, IType type, IExpression value){
        this.id = id;
        this.type = type;
        this.value = value;
    }

    @Override
    public String toString() {
        return "\n  Variable Declaration {" +
                "Var_id=" + id + '\'' +
                "Var_type=" + type + '\'' +
                "Var_value=" + value + '\'' +
                '}';
    }

    @Override
    public void emit(ClassWriter cw, MethodVisitor mv) {
        String descriptor;
        if (type instanceof PrimitiveType) {
            switch (((PrimitiveType) type).primitivetype) {
                case "boolean" -> {
                    descriptor = "Z";
                }
                case "real" -> {
                    descriptor = "D";
                }
                default -> {
                    descriptor = "I";
                }
            }
            st.put(id.v, descriptor);
            cw.visitField(ACC_PUBLIC + ACC_STATIC, id.v, descriptor, null, null);
            if (value != null) {
                value.emit(cw, mv);
                mv.visitFieldInsn(PUTSTATIC, "MetaMain", id.v, descriptor);
            }
        }
        else if (type == null) {
            var type = value.resolve_type();
            cw.visitField(ACC_PUBLIC + ACC_STATIC, id.v, type, null, null);
            if (value != null) {
                value.emit(cw, mv);
                mv.visitFieldInsn(PUTSTATIC, "MetaMain", id.v, type);
            }
        }
    }
}
