package simple.expression;

import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;
import simple.IExpression;
import simple.Operation;
import simple.Triple;

import java.util.HashMap;

import static org.objectweb.asm.Opcodes.*;

public class AndOperation implements IExpression {

    HashMap<Triple<String, String, Integer>, Integer> result_type;
    Operation operation;
    IExpression left;
    IExpression right;

    public AndOperation(String operation, IExpression left, IExpression right){
        this.left = left;
        this.right = right;
        this.operation = Operation.valueOf(operation);
        this.result_type = new HashMap<>();
        String[] types = new String[] {"I", "F", "Z"};
        result_type.put(new Triple("I", "I", Operation.PLUS), IADD);
        result_type.put(new Triple("I", "F", Operation.PLUS), IADD);
    }

    @Override
    public String toString() {
        return "\n    AND Operation{" +
                "left='" + left + '\'' +
                "Operation " + operation.name() + '\'' +
                "right=" + right + '\'' +
                '}';
    }

    @Override
    public void emit(ClassWriter cw, MethodVisitor mv, String methodName, int maxDepth) {
        String left_exp = left.resolve_type(methodName);
        String right_exp = right.resolve_type(methodName);

        if (left_exp.equals("Z") && right_exp.equals("Z")) {
            left.emit(cw, mv, methodName, maxDepth);
            right.emit(cw, mv, methodName, maxDepth);
            mv.visitInsn(IAND);
        }
        else {
            throw new RuntimeException(
                    String.format("Routine %s: and operation between %s and %s is not possible.",
                            methodName, left_exp, right_exp)
            );
        }

    }

    @Override
    public String resolve_type(String methodName) {

        String left_exp = left.resolve_type(methodName);
        String right_exp = right.resolve_type(methodName);

        if (left_exp.equals("Z") && right_exp.equals("Z")) {
            return "Z";
        }
        else {
            System.out.println("Something wrong with types");
            return null;
        }

    }

}
