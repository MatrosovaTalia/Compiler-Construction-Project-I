package simple;

import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;

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
    public String resolve_type() {
        return null;
//                st.getVariable(id.v, ).getVariableType().resolve();
    }

    @Override
    public void emit(ClassWriter cw, MethodVisitor mv, String methodName) {

    }
}
