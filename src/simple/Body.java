package simple;

import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;

import java.util.ArrayList;

public class Body extends ArrayList<IStatement> implements ILexem {




    @Override
    public void emit(ClassWriter cw, MethodVisitor mv, String methodName, int maxDepth) {

    }
}
