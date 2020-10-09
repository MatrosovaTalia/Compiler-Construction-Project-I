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
    public void emit(ClassWriter cw, MethodVisitor mv, String methodName) {
        mv.visitLdcInsn(v.doubleValue());
    }

    @Override
    public Object resolve_value() {
        return v.doubleValue();
    }

    @Override
    public String resolve_type() {
        return "D";
    }
}
