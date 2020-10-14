package simple.expression;

import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;
import simple.IExpression;
import simple.Operation;
import simple.Triple;

import java.util.HashMap;

import static org.objectweb.asm.Opcodes.*;
import static org.objectweb.asm.Opcodes.FADD;

public class PlusOperation implements IExpression {

    HashMap<Triple<String, String, Integer>, Integer> result_type;
    Operation operation;
    IExpression left;
    IExpression right;

    public PlusOperation(String operation, IExpression left, IExpression right){
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
        return "\n    Plus Operation{" +
                "left='" + left + '\'' +
                "Operation " + operation.name() + '\'' +
                "right=" + right + '\'' +
                '}';
    }

    @Override
    public void emit(ClassWriter cw, MethodVisitor mv, String methodName, int maxDepth) {
        String left_exp = left.resolve_type(methodName);
        String right_exp = right.resolve_type(methodName);

        if (left_exp.equals("I") && right_exp.equals("I")) {
            left.emit(cw, mv, methodName, maxDepth);
            right.emit(cw, mv, methodName, maxDepth);
            mv.visitInsn(IADD);
        }
        else if (left_exp.equals("I") && right_exp.equals("F")) {
            left.emit(cw, mv, methodName, maxDepth);
            mv.visitInsn(I2F);
            right.emit(cw, mv, methodName, maxDepth);
            mv.visitInsn(FADD);
        }
        else if (left_exp.equals("I") && right_exp.equals("Z")) {
            throw new RuntimeException("Illegal expression: Arithmetical operation does not accept boolean value");
        }
        else if (left_exp.equals("F") && right_exp.equals("F")) {
            left.emit(cw, mv, methodName, maxDepth);
            right.emit(cw, mv, methodName, maxDepth);
            mv.visitInsn(FADD);
        }
        else if (left_exp.equals("Z") && right_exp.equals("Z")) {
            throw new RuntimeException("Illegal expression: Arithmetical operation does not accept boolean value");
        }
        else if (left_exp.equals("F") && right_exp.equals("I")) {
            left.emit(cw, mv, methodName, maxDepth);
            right.emit(cw, mv, methodName, maxDepth);
            mv.visitInsn(I2F);
            mv.visitInsn(FADD);
        }
        else if (left_exp.equals("F") && right_exp.equals("Z")) {
            throw new RuntimeException("Illegal expression: Arithmetical operation does not accept boolean value");
        }
        else if (left_exp.equals("Z") && right_exp.equals("I")) {
            throw new RuntimeException("Illegal expression: Arithmetical operation does not accept boolean value");
        }
        else if (left_exp.equals("Z") && right_exp.equals("F")) {
            throw new RuntimeException("Illegal expression: Arithmetical operation does not accept boolean value");
        }

    }

    @Override
    public String resolve_type(String methodName) {

        String left_exp = left.resolve_type(methodName);
        String right_exp = right.resolve_type(methodName);

        if (left_exp.equals("F") || right_exp.equals("F")) {
            return "F";
        }
        else if (left_exp.equals("I") && right_exp.equals("I")){
            return "I";
        }
        else {
            System.out.println("Something wrong with types");
            return null;
        }

    }

}
