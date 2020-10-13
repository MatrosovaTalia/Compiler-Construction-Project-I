package simple;

import org.jetbrains.annotations.NotNull;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;

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
    public void emit(ClassWriter cw, MethodVisitor mv, String methodName, int maxDepth) {
        String varName = id.v;
        if (st.localVariableExists(methodName, varName)) {
            throw new RuntimeException(String.format("Local variable with name %s already exists in method %s!", id.v, methodName));
        }
        String desc = "I";
        if (type == null) {
            desc = value.resolve_type(methodName);
            value.emit(cw, mv, methodName, maxDepth);
            st.addVariableToMethod(varName, methodName, desc);
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
            st.addVariableToMethod(varName, methodName, desc);
            var index = st.getLocalVariableIndex(methodName, varName);
            var firstChar = desc.charAt(0);
            switch (firstChar) {
                case 'F' -> {
                    mv.visitInsn(FCONST_0);
                    mv.visitVarInsn(FSTORE, index);
                }
                case 'I', 'Z' -> {
                    mv.visitInsn(ICONST_0);
                    mv.visitVarInsn(ISTORE, index);
                }
                case '[' -> {
                    type.emit(cw, mv, methodName, ArrayType.maxDepth(type.resolve()));
                    mv.visitVarInsn(ASTORE, index);
                }
            }
        }
        else {
            value.emit(cw, mv, methodName, maxDepth);

            var index = st.getLocalVariableIndex(methodName, varName);
            String expectedType = type.resolve();
            String givenType = value.resolve_type(methodName);
            desc = expectedType;
            st.addVariableToMethod(varName, methodName, desc);

            castIfNecessary(mv, methodName, varName, expectedType, givenType);

            var firstChar = desc.charAt(0);
            switch (firstChar) {
                case 'F' -> mv.visitVarInsn(FSTORE, index);
                case 'I', 'Z' -> mv.visitVarInsn(ISTORE, index);
            }
        }
    }

    static void castIfNecessary(MethodVisitor mv, String methodName, String varName, @NotNull String expectedType, String givenType) {
        if (expectedType.equals("I") && givenType.equals("F")) {
            mv.visitInsn(F2I)              ;
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
    }
}
