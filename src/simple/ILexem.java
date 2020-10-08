package simple;

import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;

public interface ILexem {
    static SymbolTable st = new SymbolTable();
    void emit(ClassWriter cw, MethodVisitor mv);
}
