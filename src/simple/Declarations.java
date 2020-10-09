package simple;

import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;

import java.util.ArrayList;
import java.util.function.IntFunction;
import java.util.stream.Stream;

public class Declarations extends ArrayList<IDeclaration> implements ILexem {
    @Override
    public void emit(ClassWriter cw, MethodVisitor mv) {

    }

}
