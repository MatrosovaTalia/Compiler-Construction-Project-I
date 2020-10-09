package simple;

import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes.*;

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
    public void emit(ClassWriter cw, MethodVisitor mv) {
        StringBuilder descriptor = new StringBuilder("(");
        if (params.size() > 0) {
            for (var param : params) {
                descriptor.append(param.type == null ? "V" : param.type.resolve());
            }
        }
        else {
            descriptor.append("V");
        }
        descriptor.append(")");
        descriptor.append(returnType == null ? "V" : returnType.resolve());
        MethodVisitor new_method = cw.visitMethod(
                ACC_PUBLIC + ACC_STATIC,
                (name.v.equals("main") ? name.v + "_" : name.v),
                descriptor.toString(),
                null,
                new String[0]
        );
        for (var statement: body) {
            statement.emit(cw, new_method);
        }
        if (returnType == null) {
            new_method.visitInsn(RETURN);
        }
        new_method.visitMaxs(-1, -1);
        new_method.visitEnd();

    }
}
