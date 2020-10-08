package simple;

import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;

import java.util.ArrayList;

public class RecordType extends ArrayList<VariableDeclaration> implements IType{

    @Override
    public void emit(ClassWriter cw, MethodVisitor mv) {

    }

    @Override
    public String resolve() {
        return null;
    }
}
