package simple;


import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;

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

    }
}
