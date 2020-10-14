package simple;

import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;

public class TypeDeclaration implements IDeclaration{
    public Identifier id;
    public IType type;
    public TypeDeclaration(Identifier id, IType type){
        this.id = id;
        this.type = type;
    }

    @Override
    public String toString() {
        return "\nType Declaration {" +
                "Type name=" + id + '\'' +
                "type=" + type + '\'' +
                '}';
    }

    @Override
    public void emit(ClassWriter cw, MethodVisitor mv, String methodName, int maxDepth) {

    }
}
