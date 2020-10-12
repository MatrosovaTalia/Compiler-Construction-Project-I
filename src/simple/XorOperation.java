package simple;

import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;

import java.util.HashMap;

import static org.objectweb.asm.Opcodes.*;
import static org.objectweb.asm.Opcodes.FADD;

public class XorOperation implements IExpression{

    HashMap<Triple<String, String, Integer>, Integer> result_type;
    Operation operation;
    IExpression left;
    IExpression right;

    public XorOperation(String operation, IExpression left, IExpression right){
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
        return "\n    XOR Operation{" +
                "left='" + left + '\'' +
                "Operation " + operation.name() + '\'' +
                "right=" + right + '\'' +
                '}';
    }

    @Override
    public void emit(ClassWriter cw, MethodVisitor mv, String methodName) {
        String left_exp = left.resolve_type(methodName);
        String right_exp = right.resolve_type(methodName);

        if (left_exp.equals("I") && right_exp.equals("I")) {
            left.emit(cw, mv, methodName);
            right.emit(cw, mv, methodName);
            mv.visitInsn(IXOR);
        }
        else if (left_exp.equals("I") && right_exp.equals("F")) {
            throw new RuntimeException("Illegal expression: Bitwise operation does not accept real value");
        }
        else if (left_exp.equals("I") && right_exp.equals("Z")) {
            left.emit(cw, mv, methodName);
            right.emit(cw, mv, methodName);
            mv.visitInsn(IXOR);
        }
        else if (left_exp.equals("F") && right_exp.equals("F")) {
            throw new RuntimeException("Illegal expression: Bitwise operation does not accept real value");
        }
        else if (left_exp.equals("Z") && right_exp.equals("Z")) {
            left.emit(cw, mv, methodName);
            right.emit(cw, mv, methodName);
            mv.visitInsn(IXOR);
        }
        else if (left_exp.equals("F") && right_exp.equals("I")) {
            throw new RuntimeException("Illegal expression: Bitwise operation does not accept real value");
        }
        else if (left_exp.equals("F") && right_exp.equals("Z")) {
            throw new RuntimeException("Illegal expression: Bitwise operation does not accept real value");
        }
        else if (left_exp.equals("Z") && right_exp.equals("I")) {
            left.emit(cw, mv, methodName);
            right.emit(cw, mv, methodName);
            mv.visitInsn(IXOR);
        }
        else if (left_exp.equals("Z") && right_exp.equals("F")) {
            throw new RuntimeException("Illegal expression: Bitwise operation does not accept real value");
        }

    }

    @Override
    public Object resolve_value() {
        return null;
    }

    @Override
    public String resolve_type(String methodName) {

        String left_exp = left.resolve_type(methodName);
        String right_exp = right.resolve_type(methodName);

        if (left_exp.equals("F") || right_exp.equals("F")) {
            return "F";
        }
        else if (left_exp.equals("I") || left_exp.equals("Z") ||
                right_exp.equals("I") || right_exp.equals("Z")){
            return "I";
        }
        else {
            System.out.println("Something wrong with types");
            return null;
        }

    }

}
