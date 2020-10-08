package simple;

import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;

public class IndexAccess implements IPrimary{
    IExpression expression;

    public IndexAccess(IExpression expression){
        this.expression = expression;
    }

    @Override
    public String toString() {
        return "\nIndex_Access{" +
                "expression=" + expression + '\'' +
                '}';
    }

    @Override
    public void emit(ClassWriter cw, MethodVisitor mv) {

    }

    @Override
    public Object resolve_value() {
        return null;
    }

    @Override
    public String resolve_type() {
        return "";
    }
}
