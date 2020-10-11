package simple;

import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;

import java.util.Collections;

import static org.objectweb.asm.Opcodes.*;

public class WhileStatement implements IStatement{
    IExpression expression;
    Body body;

    public WhileStatement(IExpression expression, Body body){
        this.expression = expression;
        this.body = body;
        Collections.reverse(this.body);
    }

    @Override
    public String toString() {
        return "\n  While {" +
                "while_exp=" + expression + '\'' +
                "while_body=" + body + '\'' +
                '}';
    }

    @Override
    public void emit(ClassWriter cw, MethodVisitor mv, String methodName) {
        Label start = new Label();
        Label end = new Label();
        mv.visitLabel(start);
        expression.emit(cw, mv, methodName);
        if (expression.resolve_type(methodName).equals("F")) {
            mv.visitInsn(F2I);
        }
        mv.visitInsn(ICONST_0);
        mv.visitJumpInsn(IF_ICMPEQ, end);
        for (var st: body) {
            st.emit(cw, mv, methodName);
        }
        mv.visitJumpInsn(GOTO, start);
        mv.visitLabel(end);
    }
}
