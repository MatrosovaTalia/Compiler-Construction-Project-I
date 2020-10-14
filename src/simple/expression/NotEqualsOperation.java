package simple.expression;

import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;
import simple.IExpression;
import simple.Operation;
import simple.Triple;

import java.util.HashMap;

import static org.objectweb.asm.Opcodes.*;

public class NotEqualsOperation implements IExpression {

    HashMap<Triple<String, String, Integer>, Integer> result_type;
    Operation operation;
    IExpression left;
    IExpression right;

    public NotEqualsOperation(String operation, IExpression left, IExpression right){
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
        return "\n    Not Equals Operation{" +
                "left='" + left + '\'' +
                "Operation " + operation.name() + '\'' +
                "right=" + right + '\'' +
                '}';
    }

    @Override
    public void emit(ClassWriter cw, MethodVisitor mv, String methodName, int maxDepth) {
        String left_exp = left.resolve_type(methodName);
        String right_exp = right.resolve_type(methodName);
        if (left_exp.equals("Z") || right_exp.equals("Z")) {
            throw new RuntimeException(String.format("Routine %s: Comparison between %s and %s is forbidden.",
                    methodName, left_exp, right_exp));
        }
        left.emit(cw, mv, methodName, maxDepth);
        if (left_exp.equals("I")) {
            mv.visitInsn(I2F);
        }
        right.emit(cw, mv, methodName, maxDepth);
        if (right_exp.equals("I")) {
            mv.visitInsn(I2F);
        }
        Label True = new Label();
        Label End = new Label();
        mv.visitInsn(FCMPG);
        mv.visitLdcInsn(0);
        mv.visitJumpInsn(IF_ICMPNE, True);
        mv.visitInsn(ICONST_0);
        mv.visitJumpInsn(GOTO, End);
        mv.visitLabel(True);
        mv.visitInsn(ICONST_1);
        mv.visitLabel(End);
    }

    @Override
    public String resolve_type(String methodName) {

        String left_exp = left.resolve_type(methodName);
        String right_exp = right.resolve_type(methodName);

        if ((left_exp.equals("F") && right_exp.equals("F")) ||
                (left_exp.equals("I") && right_exp.equals("I")) ||
                (left_exp.equals("I") && right_exp.equals("F")) ||
                (left_exp.equals("F") && right_exp.equals("I")) ||
                (left_exp.equals("Z") && right_exp.equals("Z"))) {
            return "Z";
        }
        else {
            System.out.println("Something wrong with types");
            return null;
        }

    }

}
