package simple;

import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;

public class Identifier implements ILexem {
    public String v;

    public Identifier(String v) {
        this.v = v;
    }

    @Override
    public String toString() {
        return "Identifier{" +
                "id=" + v + '\'' +
                '}';
    }

    @Override
    public void emit(ClassWriter cw, MethodVisitor mv, String methodName) {

    }
}
