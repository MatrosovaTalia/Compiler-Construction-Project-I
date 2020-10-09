package simple;


import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;

import static org.objectweb.asm.Opcodes.GETSTATIC;
import static org.objectweb.asm.Opcodes.INVOKEVIRTUAL;

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
    public void emit(ClassWriter cw, MethodVisitor mv, String methodName) {
        for (var exp: expressions) {
            mv.visitFieldInsn(GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
            exp.emit(cw, mv, methodName);
            mv.visitMethodInsn(INVOKEVIRTUAL,
                    "java/io/PrintStream",
                    "println",
                    "(" + exp.resolve_type() + ")V",
                    false);
        }
    }
}
