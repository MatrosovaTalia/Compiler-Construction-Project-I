package simple;


import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;

import static org.objectweb.asm.Opcodes.RETURN;

public class Return implements IStatement {
    IExpression expression;

    public Return(IExpression expression) {
        this.expression = expression;
    }

    @Override
    public String toString() {
        return "\nReturn{" +
                "expression=" + expression + '\'' +
                '}';
    }

    @Override
    public void emit(ClassWriter cw, MethodVisitor mv) {
        expression.emit(cw, mv);
        mv.visitInsn(RETURN);
    }
}
