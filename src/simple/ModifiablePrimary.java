package simple;

import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;

import static org.objectweb.asm.Opcodes.GETSTATIC;

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
    public void emit(ClassWriter cw, MethodVisitor mv, String methodName) {
        mv.visitFieldInsn(GETSTATIC, "MetaMain", id.v, st.get(id.v));
    }

    @Override
    public Object resolve_value() {
        return null;
    }

    @Override
    public String resolve_type() {
        return st.get(id.v);
    }
}
