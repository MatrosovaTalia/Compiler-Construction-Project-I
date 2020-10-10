package simple;

import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;

import static org.objectweb.asm.Opcodes.*;

public class ModifiablePrimary implements IExpression{
    Identifier id;
    ElementCall callList;

    public ModifiablePrimary(Identifier id, ElementCall callList){
        this.id = id;
        this.callList = callList;
    }

    @Override
    public String toString() {
        return "\nModifiablePrimary{" +
                "id=" + id + '\'' +
                "Element_calls" + callList + '\'' +
                '}';
    }

    @Override
    public Object resolve_value() {
        //        mv.visitFieldInsn(GETSTATIC, "MetaMain", id.v, st.getVariable(id.v, ).getVariableType().resolve());
        return null;
    }

    @Override
    public String resolve_type(String methodName) {
        var varName = id.v;
        if (st.localVariableExists(methodName, varName)) {
            var variable = st.getLocalVariable(methodName, id.v);
            var type = variable.getVariableType();
            return type;
        }
        else {
            if (st.globalVariableExists(varName)) {
                var variable = st.getGlobalVariable(varName);
                var type = variable.getVariableType();
                return type;
            }
            else {
                throw new RuntimeException(String.format("No variable has been declared with such name: %s", varName));
            }
        }
    }

    @Override
    public void emit(ClassWriter cw, MethodVisitor mv, String methodName) {
        String varName = id.v;
        if (st.localVariableExists(methodName, varName)) {
            var variable = st.getLocalVariable(methodName, varName);
            var type = variable.getVariableType();
            var index = st.getLocalVariableIndex(methodName, varName);
            if (type.equals("F")) {
                mv.visitVarInsn(FLOAD, index);
            }
            else {
                mv.visitVarInsn(ILOAD, index);
            }
        }
        else {
            if (st.globalVariableExists(varName)) {
                var variable = st.getGlobalVariable(varName);
                var type = variable.getVariableType();
                mv.visitFieldInsn(GETSTATIC, "MetaMain", varName, type);
            }
            else {
                throw new RuntimeException(String.format("There is no variable with such name: %s", varName));
            }
        }
    }
}
