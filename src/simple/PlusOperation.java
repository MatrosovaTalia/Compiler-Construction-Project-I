package simple;

import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;

import java.util.HashMap;

import static org.objectweb.asm.Opcodes.*;
import static org.objectweb.asm.Opcodes.FADD;

public class PlusOperation implements IExpression{

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
    public void emit(ClassWriter cw, MethodVisitor mv, String methodName) {
        String left_exp = left.resolve_type(methodName);
        String right_exp = right.resolve_type(methodName);

        if (left_exp.equals("I") && right_exp.equals("I")) {
            left.emit(cw, mv, methodName);
            right.emit(cw, mv, methodName);
            mv.visitInsn(IADD);
        }
        else if (left_exp.equals("I") && right_exp.equals("F")) {
            left.emit(cw, mv, methodName);
            mv.visitInsn(I2F);
            right.emit(cw, mv, methodName);
            mv.visitInsn(FADD);
        }
        else if (left_exp.equals("I") && right_exp.equals("Z")) {
            left.emit(cw, mv, methodName);
            right.emit(cw, mv, methodName);
            mv.visitInsn(IADD);
        }
        else if (left_exp.equals("F") && right_exp.equals("F")) {
            left.emit(cw, mv, methodName);
            right.emit(cw, mv, methodName);
            mv.visitInsn(FADD);
        }
        else if (left_exp.equals("Z") && right_exp.equals("Z")) {
            left.emit(cw, mv, methodName);
            right.emit(cw, mv, methodName);
            mv.visitInsn(IADD);
        }
        else if (left_exp.equals("F") && right_exp.equals("I")) {
            left.emit(cw, mv, methodName);
            right.emit(cw, mv, methodName);
            mv.visitInsn(I2F);
            mv.visitInsn(FADD);
        }
        else if (left_exp.equals("F") && right_exp.equals("Z")) {
            left.emit(cw, mv, methodName);
            right.emit(cw, mv, methodName);
            mv.visitInsn(I2F);
            mv.visitInsn(FADD);
        }
        else if (left_exp.equals("Z") && right_exp.equals("I")) {
            left.emit(cw, mv, methodName);
            right.emit(cw, mv, methodName);
            mv.visitInsn(IADD);
        }
        else if (left_exp.equals("Z") && right_exp.equals("F")) {
            left.emit(cw, mv, methodName);
            mv.visitInsn(I2F);
            right.emit(cw, mv, methodName);
            mv.visitInsn(FADD);
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
