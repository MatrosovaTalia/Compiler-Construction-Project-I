package simple;

import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;

import java.util.Collections;

import static org.objectweb.asm.Opcodes.*;

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
        Collections.reverse(this.params);
        Collections.reverse(this.body);
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
    public void emit(ClassWriter cw, MethodVisitor mv, String methodName, int maxDepth) {
        StringBuilder descriptor = new StringBuilder("(");
        String name = this.name.v.equals("main") ? this.name.v + "_" : this.name.v;
        for (var param : params) {
            descriptor.append(param.type.resolve());
            param.emit(cw, mv, name, maxDepth);
        }
        descriptor.append(")");
        descriptor.append(returnType == null ? "V" : returnType.resolve());
        st.addMethod(name, descriptor.toString(), params);
        MethodVisitor new_method = cw.visitMethod(
                ACC_PUBLIC + ACC_STATIC,
                name,
                descriptor.toString(),
                null,
                new String[0]
        );
        for (var statement: body) {
            statement.emit(cw, new_method, name, maxDepth);
        }
        if (returnType == null) {
            new_method.visitInsn(RETURN);
        }
        new_method.visitMaxs(-1, -1);
        new_method.visitEnd();

    }
}
