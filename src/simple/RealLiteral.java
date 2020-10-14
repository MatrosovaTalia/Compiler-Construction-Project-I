package simple;


import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;

import java.math.BigDecimal;

public class RealLiteral implements IExpression {
    public final BigDecimal v;

    public RealLiteral(BigDecimal v){
        this.v = v;
    }

    @Override
    public String toString() {
        return "RealLiteral{" +
                "v=" + v +
                '}';
    }

    @Override
    public void emit(ClassWriter cw, MethodVisitor mv, String methodName, int maxDepth) {
        mv.visitLdcInsn(v.floatValue());
    }

    @Override
    public String resolve_type(String methodName) {
        return "F";
    }
}
