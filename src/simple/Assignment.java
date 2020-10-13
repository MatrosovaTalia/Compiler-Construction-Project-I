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
    public void emit(ClassWriter cw, MethodVisitor mv, String methodName, int maxDepth) {
        String varName = m_primary.id.v;
        if (st.localVariableExists(methodName, varName)) {
            var index = st.getLocalVariableIndex(methodName, varName);
            var variable = st.getLocalVariable(methodName, varName);
            var expectedType = variable.getVariableType();
            var givenType = expression.resolve_type(methodName);
            LocalVariableDeclaration.castIfNecessary(mv, methodName, varName, expectedType, givenType);
            var firstChar = variable.getVariableType().charAt(0);
            switch (firstChar) {
                case 'F' -> {
                    expression.emit(cw, mv, methodName, maxDepth);
                    mv.visitVarInsn(FSTORE, index);
                }
                case 'I', 'Z' -> {
                    expression.emit(cw, mv, methodName, maxDepth);
                    mv.visitVarInsn(ISTORE, index);
                }
                case '[' -> {
                    m_primary.setReference(true);
                    m_primary.emit(cw, mv, methodName, maxDepth);
                    expression.emit(cw, mv, methodName, maxDepth);
                    expectedType = m_primary.resolve_type(methodName);
                    givenType = expression.resolve_type(methodName);
                    var char1 = givenType.charAt(0);
                    switch (char1) {
                        case 'F' -> mv.visitInsn(FASTORE);
                        case 'I', 'Z' -> mv.visitInsn(IASTORE);
                        case '[' -> {
                            if (givenType.equals(expectedType)) {
                                mv.visitInsn(AASTORE);
                            }
                            else {
                                throw new RuntimeException(
                                        String.format("Routine %s: attempt to assign the array of incompatible type!",
                                                methodName
                                        )
                                );
                            }
                        }
                    }
                }
            }
        }
        else {
            if (st.globalVariableExists(varName)) {
                var variable = st.getGlobalVariable(varName);
                var expectedType = variable.getVariableType();
                var givenType = expression.resolve_type(methodName);
                expression.emit(cw, mv, methodName, maxDepth);
                LocalVariableDeclaration.castIfNecessary(mv, methodName, varName, expectedType, givenType);
                mv.visitFieldInsn(PUTSTATIC, "MetaMain", varName, expectedType);
            }
            else {
                throw new RuntimeException(String.format("Variable you are trying to assign to doesn't exist: %s", varName));
            }
        }
    }
}
