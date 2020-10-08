package simple;

import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;

import java.util.ArrayList;

public class Parameters extends ArrayList<Parameter> implements ILexem {
    @Override
    public void emit(ClassWriter cw, MethodVisitor mv) {

    }
}
