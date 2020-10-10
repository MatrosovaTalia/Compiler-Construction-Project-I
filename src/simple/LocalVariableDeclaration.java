package simple;

import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;

import java.math.BigDecimal;
import java.math.BigInteger;

import static org.objectweb.asm.Opcodes.*;

public class LocalVariableDeclaration implements IDeclaration {

    public Identifier id;
    public IType type;
    public IExpression value;


    public LocalVariableDeclaration(VariableDeclaration decl){
        this.id = decl.id;
        this.type = decl.type;
        this.value = decl.value;
    }

    @Override
    public String toString() {
        return "\n  LocalVariableDeclaration {" +
                "Var_id=" + id + '\'' +
                "Var_type=" + type + '\'' +
                "Var_value=" + value + '\'' +
                '}';
    }
    @Override
    public void emit(ClassWriter cw, MethodVisitor mv, String methodName) {
        String varName = id.v;
        if (st.localVariableExists(methodName, varName)) {
            throw new RuntimeException(String.format("Local variable with name %s already exists in method %s!", id.v, methodName));
        }
        String desc = "I";
        if (type == null) {
            desc = value.resolve_type(methodName);
            value.emit(cw, mv, methodName);
            st.addVariableToMethod(varName, methodName, desc, value);
            var index = st.getLocalVariableIndex(methodName, varName);
            if (desc.equals("F")) {
                mv.visitVarInsn(FSTORE, index);
            }
            else {
                mv.visitVarInsn(ISTORE, index);
            }
        }
        else if (value == null) {
            desc = type.resolve();
            IExpression defaultValue;
            if (desc.equals("F")) {
                defaultValue = new RealLiteral(BigDecimal.ZERO);
            }
            else {
                defaultValue = new IntegerLiteral(BigInteger.ZERO);
            }
            st.addVariableToMethod(varName, methodName, desc, defaultValue);
            var index = st.getLocalVariableIndex(methodName, varName);
            if (desc.equals("F")) {
                mv.visitInsn(FCONST_0);
                mv.visitVarInsn(FSTORE, index);
            }
            else {
                mv.visitInsn(ICONST_0)              ;
                mv.visitVarInsn(ISTORE, index);
            }
        }
        else {
            String expectedType = type.resolve();
            String givenType = value.resolve_type(methodName);
            value.emit(cw, mv, methodName);
            if (expectedType.equals("I") && givenType.equals("I")) {
                desc = "I";
            } else if (expectedType.equals("I") && givenType.equals("F")) {
                desc = "I";
                mv.visitInsn(F2I)              ;
            } else if (expectedType.equals("I") && givenType.equals("Z")) {
                desc = "I";
            } else if (expectedType.equals("F") && givenType.equals("F")) {
                desc = "F";
            } else if (expectedType.equals("Z") && givenType.equals("Z")) {
                desc = "Z";
            } else if (expectedType.equals("F") && givenType.equals("I")) {
                mv.visitInsn(I2F)              ;
                desc = "F";
            } else if (expectedType.equals("F") && givenType.equals("Z")) {
                mv.visitInsn(I2F)              ;
                desc = "F";
            } else if (expectedType.equals("Z") && givenType.equals("I")) {
                throw new RuntimeException(String.format("Method %s: trying to assign integer value to a boolean variable %s",
                        methodName, varName));

            } else if (expectedType.equals("Z") && givenType.equals("F")) {
                throw new RuntimeException(String.format("Method %s: trying to assign real value to a boolean variable %s",
                        methodName, varName));
            }
            st.addVariableToMethod(varName, methodName, desc, value);
            var index = st.getLocalVariableIndex(methodName, varName);
            if (desc.equals("F")) {
                mv.visitVarInsn(FSTORE, index);
            }
            else {
                mv.visitVarInsn(ISTORE, index);
            }
        }
    }
}
