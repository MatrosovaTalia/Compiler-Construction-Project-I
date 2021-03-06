package simple;

import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;

public class Parameter implements ILexem {
    public Identifier id;
    public IType type;

    public Parameter(Identifier id, IType type){
        this.id = id;
        this.type = type;
    }

    @Override
    public String toString() {
        return "\nParameter{" +
                "id=" + id + '\'' +
                "type=" + type +'\''+
                '}';
    }

    @Override
    public void emit(ClassWriter cw, MethodVisitor mv, String methodName, int maxDepth) {

    }
}
