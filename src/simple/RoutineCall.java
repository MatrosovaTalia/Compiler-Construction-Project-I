package simple;


import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;

public class RoutineCall implements IStatement {
    Identifier id;
    Expressions expressions;

    public RoutineCall(Identifier id, Expressions expressions){
        this.expressions = expressions;
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

    }
}
