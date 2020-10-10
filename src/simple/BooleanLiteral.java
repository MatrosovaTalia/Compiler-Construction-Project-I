package simple;

import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;

import static org.objectweb.asm.Opcodes.*;

public class BooleanLiteral implements IExpression {
    public final boolean v;

    public BooleanLiteral(boolean v) {
        this.v = v;
    }

    @Override
    public String toString() {
        return "BooleanLiteral{" +
                "v=" + v +
                '}';
    }

    @Override
    public void emit(ClassWriter cw, MethodVisitor mv, String methodName) {
        if (v) {
            mv.visitInsn(ICONST_1);
        }
        else {
            mv.visitInsn(ICONST_0);
        }
    }

    @Override
    public Object resolve_value() {
        return v;
    }

    @Override
    public String resolve_type(String methodName) {
        return "Z";
    }
}
