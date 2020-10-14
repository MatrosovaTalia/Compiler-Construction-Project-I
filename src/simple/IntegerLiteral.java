package simple;


import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;

import java.math.BigInteger;

import static org.objectweb.asm.Opcodes.ICONST_0;

public class IntegerLiteral implements IExpression {
    public final BigInteger v;

    public IntegerLiteral(BigInteger v){
        this.v = v;
    }

    @Override
    public String toString() {
        return "IntegerLiteral{" +
                "int_value=" + v +
                '}';
    }

    @Override
    public void emit(ClassWriter cw, MethodVisitor mv, String methodName, int maxDepth) {
        switch (v.intValue()) {
            case 0, 1, 2, 3, 4, 5 -> {
                mv.visitInsn(ICONST_0 + v.intValue());
            }
            default -> {
                mv.visitLdcInsn(v.intValue());
            }

        }
    }

    @Override
    public String resolve_type(String methodName) {
        return "I";
    }
}
