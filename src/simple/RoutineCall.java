package simple;


import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;

import java.util.Collections;

import static org.objectweb.asm.Opcodes.INVOKESTATIC;

public class RoutineCall implements IStatement, IExpression {
    Identifier id;
    Expressions expressions;

    public RoutineCall(Identifier id, Expressions expressions){
        this.expressions = expressions;
        Collections.reverse(this.expressions);
        this.id = id;
    }

    @Override
    public String toString() {
        return "\n    Routine_call{" +
                "id='" + id + '\'' +
                "Parameters " + expressions + '\'' +
                '}';
    }

    @Override
    public void emit(ClassWriter cw, MethodVisitor mv, String methodName) {
        if (st.methodExists(id.v)) {
            var method = st.getMethod(id.v);
            for (var exp : expressions) {
                exp.emit(cw, mv, methodName);
            }
            mv.visitMethodInsn(INVOKESTATIC, "MetaMain", id.v, method.descriptor);
        }
        else {
            throw new RuntimeException(
                    String.format("Routine %s: Routine you are trying to call (%s) hasn't been declared!", methodName, id.v)
            );
        }
    }

    @Override
    public Object resolve_value() {
        return null;
    }

    @Override
    public String resolve_type(String methodName) {
        return st.getMethod(id.v).getReturnType();
    }
}
