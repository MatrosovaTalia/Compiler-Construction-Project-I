package simple;


import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;

import java.util.Collections;

import static org.objectweb.asm.Opcodes.*;

public class ForLoop implements IStatement {
    public Identifier id;
    public IExpression from;
    public IExpression to;
    public boolean reverse;
    public Body body;

    public ForLoop (Identifier identifier, IExpression from, IExpression to, Body body, boolean r) {
        this.id = identifier;
        this.body = body;
        Collections.reverse(body);
        this.from = from;
        this.to = to;
        this.reverse = r;
    }

    @Override
    public String toString() {
        return "\n  For_loop{" +
                "i=" + id + '\'' +
                "from=" + from +'\''+
                "to=" + to +'\''+
                "reverse?=" + reverse +'\''+
                "Body=" + body +'\''+
                '}';
    }

    @Override
    public void emit(ClassWriter cw, MethodVisitor mv, String methodName, int maxDepth) {
        String varName = id.v;
        Label start = new Label();
        Label end = new Label();
        st.addVariableToMethod(varName, methodName, "I");
        st.addVariableToMethod("$", methodName, to.resolve_type(methodName));

        var inc = 1;
        var cmp = IF_ICMPGE;
        if (reverse) {
            inc = -1;
            cmp = IF_ICMPLE;
        }
        if ((!from.resolve_type(methodName).equals("I")) ||
                (!to.resolve_type(methodName).equals("I"))) {
            throw new RuntimeException(
                    String.format(
                            "Routine %s: some of the range boundaries does not resolve to integer!",
                            methodName)
            );
        }

        var i = st.getLocalVariableIndex(methodName, varName);
        var to = st.getLocalVariableIndex(methodName, "$");
        if (this.from instanceof ModifiablePrimary) {
            ((ModifiablePrimary) this.from).setReference(false);
        }
        this.from.emit(cw, mv, methodName, maxDepth);
        mv.visitVarInsn(ISTORE, i);
        if (this.to instanceof ModifiablePrimary) {
            ((ModifiablePrimary) this.to).setReference(false);
        }
        this.to.emit(cw, mv, methodName, maxDepth);
        mv.visitVarInsn(ISTORE, to);

        mv.visitLabel(start);
        mv.visitVarInsn(ILOAD, i);
        mv.visitVarInsn(ILOAD, to);
        mv.visitJumpInsn(cmp, end);
        for (var stmt: body) {
            stmt.emit(cw, mv, methodName, maxDepth);
        }
        mv.visitIincInsn(i, inc);
        mv.visitJumpInsn(GOTO, start);
        mv.visitLabel(end);

        st.removeVariableFromMethod(varName, methodName);
        st.removeVariableFromMethod("$", methodName);

    }
}
