package simple;

import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;

import java.util.HashMap;

import static org.objectweb.asm.Opcodes.*;
import static org.objectweb.asm.Opcodes.FADD;

public class GEqualsOperation implements IExpression{

    HashMap<Triple<String, String, Integer>, Integer> result_type;
    Operation operation;
    IExpression left;
    IExpression right;

    public GEqualsOperation(String operation, IExpression left, IExpression right){
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
        return "\n    GEquals Operation{" +
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
            Label False = new Label();
            Label End = new Label();
            left.emit(cw, mv, methodName);
            right.emit(cw, mv, methodName);
            mv.visitInsn(ISUB);
            mv.visitInsn(ICONST_0);
            mv.visitJumpInsn(IF_ICMPLT, False);
            mv.visitInsn(ICONST_1);
            mv.visitJumpInsn(GOTO, End);
            mv.visitLabel(False);
            mv.visitInsn(ICONST_0);
            mv.visitLabel(End);
        }
        else if (left_exp.equals("I") && right_exp.equals("F")) {
            Label True = new Label();
            Label End = new Label();
            left.emit(cw, mv, methodName);
            mv.visitInsn(I2F);
            right.emit(cw, mv, methodName);
            mv.visitInsn(FCMPG);
            mv.visitInsn(ICONST_0);
            mv.visitJumpInsn(IF_ICMPGE, True);
            mv.visitInsn(FCONST_0);
            mv.visitJumpInsn(GOTO, End);
            mv.visitLabel(True);
            mv.visitInsn(FCONST_1);
            mv.visitLabel(End);
        }
        else if (left_exp.equals("I") && right_exp.equals("Z")) {
            Label False = new Label();
            Label End = new Label();
            left.emit(cw, mv, methodName);
            right.emit(cw, mv, methodName);
            mv.visitInsn(ISUB);
            mv.visitInsn(ICONST_0);
            mv.visitJumpInsn(IF_ICMPLT, False);
            mv.visitInsn(ICONST_1);
            mv.visitJumpInsn(GOTO, End);
            mv.visitLabel(False);
            mv.visitInsn(ICONST_0);
            mv.visitLabel(End);
        }
        else if (left_exp.equals("F") && right_exp.equals("F")) {
            Label True = new Label();
            Label End = new Label();
            left.emit(cw, mv, methodName);
            right.emit(cw, mv, methodName);
            mv.visitInsn(FCMPG);
            mv.visitInsn(ICONST_0);
            mv.visitJumpInsn(IF_ICMPGE, True);
            mv.visitInsn(FCONST_0);
            mv.visitJumpInsn(GOTO, End);
            mv.visitLabel(True);
            mv.visitInsn(FCONST_1);
            mv.visitLabel(End);
        }
        else if (left_exp.equals("Z") && right_exp.equals("Z")) {
            Label False = new Label();
            Label End = new Label();
            left.emit(cw, mv, methodName);
            right.emit(cw, mv, methodName);
            mv.visitInsn(ISUB);
            mv.visitInsn(ICONST_0);
            mv.visitJumpInsn(IF_ICMPLT, False);
            mv.visitInsn(ICONST_1);
            mv.visitJumpInsn(GOTO, End);
            mv.visitLabel(False);
            mv.visitInsn(ICONST_0);
            mv.visitLabel(End);
        }
        else if (left_exp.equals("F") && right_exp.equals("I")) {
            Label True = new Label();
            Label End = new Label();
            left.emit(cw, mv, methodName);
            right.emit(cw, mv, methodName);
            mv.visitInsn(I2F);
            mv.visitInsn(FCMPG);
            mv.visitInsn(ICONST_0);
            mv.visitJumpInsn(IF_ICMPGE, True);
            mv.visitInsn(FCONST_0);
            mv.visitJumpInsn(GOTO, End);
            mv.visitLabel(True);
            mv.visitInsn(FCONST_1);
            mv.visitLabel(End);
        }
        else if (left_exp.equals("F") && right_exp.equals("Z")) {
            Label True = new Label();
            Label End = new Label();
            left.emit(cw, mv, methodName);
            right.emit(cw, mv, methodName);
            mv.visitInsn(I2F);
            mv.visitInsn(FCMPG);
            mv.visitInsn(ICONST_0);
            mv.visitJumpInsn(IF_ICMPGE, True);
            mv.visitInsn(FCONST_0);
            mv.visitJumpInsn(GOTO, End);
            mv.visitLabel(True);
            mv.visitInsn(FCONST_1);
            mv.visitLabel(End);
        }
        else if (left_exp.equals("Z") && right_exp.equals("I")) {
            Label False = new Label();
            Label End = new Label();
            left.emit(cw, mv, methodName);
            right.emit(cw, mv, methodName);
            mv.visitInsn(ISUB);
            mv.visitInsn(ICONST_0);
            mv.visitJumpInsn(IF_ICMPLT, False);
            mv.visitInsn(ICONST_1);
            mv.visitJumpInsn(GOTO, End);
            mv.visitLabel(False);
            mv.visitInsn(ICONST_0);
            mv.visitLabel(End);
        }
        else if (left_exp.equals("Z") && right_exp.equals("F")) {
            Label True = new Label();
            Label End = new Label();
            left.emit(cw, mv, methodName);
            mv.visitInsn(I2F);
            right.emit(cw, mv, methodName);
            mv.visitInsn(FCMPG);
            mv.visitInsn(ICONST_0);
            mv.visitJumpInsn(IF_ICMPGE, True);
            mv.visitInsn(FCONST_0);
            mv.visitJumpInsn(GOTO, End);
            mv.visitLabel(True);
            mv.visitInsn(FCONST_1);
            mv.visitLabel(End);
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
