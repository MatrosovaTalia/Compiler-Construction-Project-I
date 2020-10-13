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
    public void emit(ClassWriter cw, MethodVisitor mv, String methodName, int maxDepth) {
        if (!resolve_type(methodName).equals("I")) {
            throw new RuntimeException(
                    String.format(
                            "Routine %s: index can only be of type integer!",
                            methodName)
            );
        }
        expression.emit(cw, mv, methodName, maxDepth);
    }

    @Override
    public String resolve_type(String methodName) {
        return expression.resolve_type(methodName);
    }
}
