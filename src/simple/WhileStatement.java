package simple;

import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;

import java.util.Collections;

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

    }
}
