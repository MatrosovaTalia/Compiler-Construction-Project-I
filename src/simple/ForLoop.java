package simple;


import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;

public class ForLoop implements IStatement {
    public Identifier id;
    public IExpression from;
    public IExpression to;
    public boolean reverse;
    public Body body;

    public ForLoop (Identifier identifier, IExpression from, IExpression to, Body body, boolean r) {
        this.id = identifier;
        this.body = body;
        this.from = from;
        this.to = to;
        this.reverse = r;
    }

    @Override
    public String toString() {
        return "\n  For_loop{" +
                "i=" + id + '\'' +
                "from=" + from +'\''+
                "to=" + to +'\''+
                "reverse?=" + reverse +'\''+
                "Body=" + body +'\''+
                '}';
    }

    @Override
    public void emit(ClassWriter cw, MethodVisitor mv) {

    }
}
