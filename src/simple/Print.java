package simple;


import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;

public class Print  implements IStatement {
    public Expressions expressions;

    public Print(Expressions expressions) {
        this.expressions = expressions;
    }

    @Override
    public String toString() {
        return "\n  Print{" +
                "expressions=" + expressions + '\'' +
                '}';
    }

    @Override
    public void emit(ClassWriter cw, MethodVisitor mv) {

    }
}
