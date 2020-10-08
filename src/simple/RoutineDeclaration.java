package simple;

import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;

public class RoutineDeclaration implements IDeclaration {
    Identifier name;
    Parameters params;
    IType returnType;
    Body body;


    public RoutineDeclaration(Identifier name, Parameters params, IType returnType, Body body){
        this.name = name;
        this.params = params;
        this.returnType = returnType;
        this.body = body;
    }

    @Override
    public String toString() {
        return "\nRoutine Declaration{" +
                "name=" + name + '\'' +
                "return type=" + returnType +'\''+
                "parameters" + params + '\'' +
                "body=" + body + '\'' +
                '}';
    }

    @Override
    public void emit(ClassWriter cw, MethodVisitor mv) {

    }
}
