package simple;

import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;

public class BinaryExpression implements IExpression{
    Operation operation;
    IExpression left;
    IExpression right;

    public BinaryExpression(String operation, IExpression left, IExpression right){
        this.left = left;
        this.right = right;
        this.operation = Operation.valueOf(operation);
    }

    @Override
    public String toString() {
        return "\n    Bin Expression{" +
                "left='" + left + '\'' +
                "Operation " + operation.name() + '\'' +
                "right=" + right + '\'' +
                '}';
    }

    @Override
    public void emit(ClassWriter cw, MethodVisitor mv) {

    }

    @Override
    public Object resolve_value() {
        switch (operation) {
            case PLUS -> {
                return ((Double) left.resolve_value()) + ((Double) right.resolve_value());
            }
        }
        return null;
    }

    @Override
    public String resolve_type() {
        return "";
    }
}
