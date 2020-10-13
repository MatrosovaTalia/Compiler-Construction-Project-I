package simple;

import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;

import java.math.BigDecimal;
import java.math.BigInteger;

import static org.objectweb.asm.Opcodes.*;

public class VariableDeclaration implements IDeclaration{
    public Identifier id;
    public IType type;
    public IExpression value;

    public VariableDeclaration(Identifier id, IType type, IExpression value){
        this.id = id;
        this.type = type;
        this.value = value;
    }

    @Override
    public String toString() {
        return "\n  Variable Declaration {" +
                "Var_id=" + id + '\'' +
                "Var_type=" + type + '\'' +
                "Var_value=" + value + '\'' +
                '}';
    }

    @Override
    public void emit(ClassWriter cw, MethodVisitor mv, String methodName, int maxDepth) {
        String varName = id.v;
        if (st.globalVariableExists(varName)) {
            throw new RuntimeException(String.format("Global variable with name %s already exists!", varName));
        }
        String desc = "I";
        if (type == null) {
            desc = value.resolve_type(methodName);
            value.emit(cw, mv, methodName, maxDepth);
            st.addGlobalVariable(varName, value.resolve_type(methodName), value);
            cw.visitField(ACC_PUBLIC + ACC_STATIC, varName, desc, null, null);
            mv.visitFieldInsn(PUTSTATIC, "MetaMain", varName, desc);
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
            st.addGlobalVariable(varName, desc, defaultValue);
            if (desc.equals("F")) {
                mv.visitInsn(FCONST_0);
            }
            else {
                mv.visitInsn(ICONST_0);
            }
            cw.visitField(ACC_PUBLIC + ACC_STATIC, varName, desc, null, null);
            mv.visitFieldInsn(PUTSTATIC, "MetaMain", varName, desc);
        }
        else {
            String expectedType = type.resolve();
            String givenType = value.resolve_type(methodName);
            value.emit(cw, mv, methodName, maxDepth);
            if (expectedType.equals("I") && givenType.equals("I")) {
                desc = "I";
            } else if (expectedType.equals("I") && givenType.equals("F")) {
                desc = "I";
                mv.visitInsn(F2I);
            } else if (expectedType.equals("I") && givenType.equals("Z")) {
                desc = "I";
            } else if (expectedType.equals("F") && givenType.equals("F")) {
                desc = "F";
            } else if (expectedType.equals("Z") && givenType.equals("Z")) {
                desc = "Z";
            } else if (expectedType.equals("F") && givenType.equals("I")) {
                mv.visitInsn(I2F);
                desc = "F";
            } else if (expectedType.equals("F") && givenType.equals("Z")) {
                mv.visitInsn(I2F);
                desc = "F";
            } else if (expectedType.equals("Z") && givenType.equals("I")) {
                throw new RuntimeException(String.format("Method %s: trying to assign integer value to a boolean variable %s",
                        methodName, varName));

            } else if (expectedType.equals("Z") && givenType.equals("F")) {
                throw new RuntimeException(String.format("Method %s: trying to assign real value to a boolean variable %s",
                        methodName, varName));
            }
            st.addGlobalVariable(varName, desc, value);
            cw.visitField(ACC_PUBLIC + ACC_STATIC, varName, desc, null, null);
            mv.visitFieldInsn(PUTSTATIC, "MetaMain", varName, desc);
        }
    }
}
