package simple;

import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;

public interface ILexem {
    void emit(ClassWriter cw, MethodVisitor mv);
}
