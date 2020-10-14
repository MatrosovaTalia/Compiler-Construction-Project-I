package simple;

import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;

import java.util.Collections;

import static org.objectweb.asm.Opcodes.*;

public class ModifiablePrimary implements IExpression{
    Identifier id;
    ElementCall calls;
    boolean reference;
    String resolved_type;

    public void setReference(boolean reference) {
        this.reference = reference;
    }

    public ModifiablePrimary(Identifier id, ElementCall callList){
        this.id = id;
        this.calls = callList;
        Collections.reverse(this.calls);
        this.reference = false;
    }

    @Override
    public String toString() {
        return "\nModifiablePrimary{" +
                "id=" + id + '\'' +
                "Element_calls" + calls + '\'' +
                '}';
    }

    @Override
    public String resolve_type(String methodName) {
        var varName = id.v;
        if (st.localVariableExists(methodName, varName)) {
            var type = resolve_array_type(methodName);
            if (indexable(type)) {
                String currentType = st.getLocalVariable(methodName, varName).getVariableType();
                for (var call : calls) {
                    if (call instanceof IndexAccess) {
                        currentType = index(currentType, methodName, varName);
                    }
                    else if (call instanceof FieldAccess){
                        if (((FieldAccess) call).id.v.equals("length")) {
                            currentType = "I";
                        }
                    }
                }
                return currentType;
            }
            return type;
        }
        else {
            if (st.globalVariableExists(varName)) {
                var variable = st.getGlobalVariable(varName);
                var type = variable.getVariableType();

                if (indexable(type)) {
                    return this.resolved_type;
                }
                return type;
            }
            else {
                throw new RuntimeException(String.format("No variable has been declared with such name: %s", varName));
            }
        }
    }

    public String resolve_array_type(String methodName) {
        return st.getLocalVariable(methodName, id.v).getVariableType();
    }

    @Override
    public void emit(ClassWriter cw, MethodVisitor mv, String methodName, int maxDepth) {
        String varName = id.v;
        if (st.localVariableExists(methodName, varName)) {
            var variable = st.getLocalVariable(methodName, varName);
            var type = variable.getVariableType();
            var index = st.getLocalVariableIndex(methodName, varName);
            var firstChar = type.charAt(0);
            switch (firstChar) {
                case 'F' -> mv.visitVarInsn(FLOAD, index);
                case 'I', 'Z' -> mv.visitVarInsn(ILOAD, index);
                case '[' -> {
                    mv.visitVarInsn(ALOAD, index);
                    this.resolved_type = st.getLocalVariable(methodName, varName).getVariableType();
                    for (int i = 0; i < calls.size(); i++) {
                        var call = calls.get(i);
                        if (call instanceof IndexAccess) {
                            this.resolved_type = index(this.resolved_type, methodName, varName);
                            ((IndexAccess) call).expression.emit(cw, mv, methodName, maxDepth);
                            if (!reference || i < calls.size() - 1) {
                                switch(this.resolved_type) {
                                    case "I", "Z" -> mv.visitInsn(IALOAD);
                                    case "F" -> mv.visitInsn(FALOAD);
                                    case "[" -> mv.visitInsn(AALOAD);
                                }
                            }
                        }
                        else if (call instanceof FieldAccess) {
                            if (((FieldAccess) call).id.v.equals("length")) {
                                mv.visitInsn(ARRAYLENGTH);
                                this.resolved_type = "I";
                            }
                            else {
                                throw new RuntimeException(
                                        String.format("Routine %s: variable %s has no attribute with name %s!",
                                                methodName, varName, ((FieldAccess) call).id.v
                                                )
                                );
                            }
                        }
                    }
                }
                default -> throw new RuntimeException("Something wrong with types!");
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
        setReference(false);
    }

    String index(String referenceType, String methodName, String varName) {
        if (indexable(referenceType)) {
            return referenceType.substring(1);
        }
        else {
            throw new RuntimeException(
                    String.format("Routine %s: variable %s of type %s is not indexable!",
                            methodName, varName, referenceType
                            )
            );
        }
    }

    private boolean indexable(String referenceType) {
        return referenceType.startsWith("[");
    }
}
