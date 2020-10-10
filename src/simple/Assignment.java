package simple;

import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;

import static org.objectweb.asm.Opcodes.*;

public class Assignment implements IStatement{
    ModifiablePrimary m_primary;
    IExpression expression;

    public Assignment(ModifiablePrimary m_primary, IExpression expression){
        this.m_primary = m_primary;
        this.expression = expression;
    }

    @Override
    public String toString() {
        return "\nAssignment{" +
                "primary=" + m_primary + '\'' +
                "expression=" + expression + '\'' +
                '}';
    }

    @Override
    public void emit(ClassWriter cw, MethodVisitor mv, String methodName) {
        String varName = m_primary.id.v;
        if (st.localVariableExists(methodName, varName)) {
            var index = st.getLocalVariableIndex(methodName, varName);
            var variable = st.getLocalVariable(methodName, varName);
            var expectedType = variable.getVariableType();
            var givenType = expression.resolve_type(methodName);
            expression.emit(cw, mv, methodName);
            if (expectedType.equals("I") && givenType.equals("F")) {
                mv.visitInsn(F2I);
            } else if (expectedType.equals("F") && givenType.equals("I")) {
                mv.visitInsn(I2F);
            } else if (expectedType.equals("F") && givenType.equals("Z")) {
                mv.visitInsn(I2F);
            } else if (expectedType.equals("Z") && givenType.equals("I")) {
                throw new RuntimeException(String.format("Method %s: trying to assign integer value to a boolean variable %s",
                        methodName, varName));
            } else if (expectedType.equals("Z") && givenType.equals("F")) {
                throw new RuntimeException(String.format("Method %s: trying to assign real value to a boolean variable %s",
                        methodName, varName));
            }
            if (expectedType.equals("F")) {
                mv.visitVarInsn(FSTORE, index);
            }
            else {
                mv.visitVarInsn(ISTORE, index);
            }
        }
        else {
            if (st.globalVariableExists(varName)) {
                var variable = st.getGlobalVariable(varName);
                var expectedType = variable.getVariableType();
                var givenType = expression.resolve_type(methodName);
                expression.emit(cw, mv, methodName);
                if (expectedType.equals("I") && givenType.equals("F")) {
                    mv.visitInsn(F2I);
                } else if (expectedType.equals("F") && givenType.equals("I")) {
                    mv.visitInsn(I2F);
                } else if (expectedType.equals("F") && givenType.equals("Z")) {
                    mv.visitInsn(I2F);
                } else if (expectedType.equals("Z") && givenType.equals("I")) {
                    throw new RuntimeException(String.format("Method %s: trying to assign integer value to a boolean variable %s",
                            methodName, varName));
                } else if (expectedType.equals("Z") && givenType.equals("F")) {
                    throw new RuntimeException(String.format("Method %s: trying to assign real value to a boolean variable %s",
                            methodName, varName));
                }
                mv.visitFieldInsn(PUTSTATIC, "MetaMain", varName, expectedType);
            }
            else {
                throw new RuntimeException(String.format("Variable you are trying to assign to doesn't exist: %s", varName));
            }
        }
    }
}
