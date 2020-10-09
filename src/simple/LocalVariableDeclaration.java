package simple;

import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;

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
    public void emit(ClassWriter cw, MethodVisitor mv) {


    }
}
