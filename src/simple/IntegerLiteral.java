package simple;


import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;

import java.math.BigInteger;

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
    public void emit(ClassWriter cw, MethodVisitor mv, String methodName) {
        mv.visitLdcInsn(v.intValue());
    }

    @Override
    public Object resolve_value() {
        return v.intValue();
    }

    @Override
    public String resolve_type() {
        return "I";
    }
}
