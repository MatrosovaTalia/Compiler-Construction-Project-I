package simple;


import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;

import static org.objectweb.asm.Opcodes.FRETURN;
import static org.objectweb.asm.Opcodes.IRETURN;

public class Return implements IStatement {
    IExpression expression;

    public Return(IExpression expression) {
        this.expression = expression;
    }

    @Override
    public String toString() {
        return "\nReturn{" +
                "expression=" + expression + '\'' +
                '}';
    }

    @Override
    public void emit(ClassWriter cw, MethodVisitor mv, String methodName, int maxDepth) {
        expression.emit(cw, mv, methodName, maxDepth);
        String type = expression.resolve_type(methodName);
        var returnType = st.getMethod(methodName).getReturnType();
        var gotType = expression.resolve_type(methodName);
        if (gotType.equals(returnType)) {
            int opcode;
            if ("F".equals(type)) {
                opcode = FRETURN;
            } else {
                opcode = IRETURN;
            }
            mv.visitInsn(opcode);
        }
        else {
            throw new RuntimeException(
                    String.format(
                            "Routine %s: routine is expected to return %s, but returns %s",
                            methodName,
                            returnType,
                            gotType
                            )
            );
        }

    }
}
