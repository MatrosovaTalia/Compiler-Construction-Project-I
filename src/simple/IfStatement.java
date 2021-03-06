package simple;


import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;

import java.util.Collections;

import static org.objectweb.asm.Opcodes.*;

public class IfStatement implements IStatement {
    public IExpression expression;
    public Body ifBody;
    public Body elseBody;


    public IfStatement(IExpression expression, Body ifBody, Body elseBody) {
        this.expression = expression;
        this.ifBody = ifBody;
        Collections.reverse(this.ifBody);
        if (elseBody != null) {
            this.elseBody = elseBody;
            Collections.reverse(this.elseBody);
        }
    }


    @Override
    public String toString() {
        return "\nIf_statement{" +
                "expression=" + expression + '\'' +
                "if=" + ifBody +'\''+
                "else=" + elseBody +'\''+
                '}';
    }

    @Override
    public void emit(ClassWriter cw, MethodVisitor mv, String methodName, int maxDepth) {
        Label if_false = new Label();
        Label end = new Label();
        expression.emit(cw, mv, methodName, maxDepth);
        if (expression.resolve_type(methodName).equals("F")) {
            mv.visitInsn(F2I);
        }
        mv.visitJumpInsn(IFEQ, if_false);
        for (var st: ifBody) {
            st.emit(cw, mv, methodName, maxDepth);
        }
        mv.visitJumpInsn(GOTO, end);
        mv.visitLabel(if_false);
        if (elseBody != null) {
            for (var st : elseBody) {
                st.emit(cw, mv, methodName, maxDepth);
            }
        }
        mv.visitLabel(end);
    }
}
