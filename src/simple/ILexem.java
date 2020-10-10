package simple;

import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;
import simple.table.SymbolTable;

public interface ILexem {
    static SymbolTable st = new SymbolTable();
    void emit(ClassWriter cw, MethodVisitor mv, String methodName);
}
