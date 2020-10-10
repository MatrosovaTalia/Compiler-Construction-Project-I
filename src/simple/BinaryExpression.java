package simple;

import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;

import java.util.HashMap;

import static org.objectweb.asm.Opcodes.*;

public class BinaryExpression implements IExpression{

    HashMap<Triple<String, String, Integer>, Integer> result_type;
    Operation operation;
    IExpression left;
    IExpression right;

    public BinaryExpression(String operation, IExpression left, IExpression right){
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
        return "\n    Bin Expression{" +
                "left='" + left + '\'' +
                "Operation " + operation.name() + '\'' +
                "right=" + right + '\'' +
                '}';
    }

    @Override
    public void emit(ClassWriter cw, MethodVisitor mv, String methodName) {
        String left_exp = left.resolve_type(methodName);
        String right_exp = right.resolve_type(methodName);

        switch (operation) {
            case PLUS -> {
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

            case MINUS -> {
                if (left_exp.equals("I") && right_exp.equals("I")) {
                    left.emit(cw, mv, methodName);
                    right.emit(cw, mv, methodName);
                    mv.visitInsn(ISUB);
                }
                else if (left_exp.equals("I") && right_exp.equals("F")) {
                    left.emit(cw, mv, methodName);
                    mv.visitInsn(I2F);
                    right.emit(cw, mv, methodName);
                    mv.visitInsn(FSUB);
                }
                else if (left_exp.equals("I") && right_exp.equals("Z")) {
                    left.emit(cw, mv, methodName);
                    right.emit(cw, mv, methodName);
                    mv.visitInsn(ISUB);
                }
                else if (left_exp.equals("F") && right_exp.equals("F")) {
                    left.emit(cw, mv, methodName);
                    right.emit(cw, mv, methodName);
                    mv.visitInsn(FSUB);
                }
                else if (left_exp.equals("Z") && right_exp.equals("Z")) {
                    left.emit(cw, mv, methodName);
                    right.emit(cw, mv, methodName);
                    mv.visitInsn(ISUB);
                }
                else if (left_exp.equals("F") && right_exp.equals("I")) {
                    left.emit(cw, mv, methodName);
                    right.emit(cw, mv, methodName);
                    mv.visitInsn(I2F);
                    mv.visitInsn(FSUB);
                }
                else if (left_exp.equals("F") && right_exp.equals("Z")) {
                    left.emit(cw, mv, methodName);
                    right.emit(cw, mv, methodName);
                    mv.visitInsn(I2F);
                    mv.visitInsn(FSUB);
                }
                else if (left_exp.equals("Z") && right_exp.equals("I")) {
                    left.emit(cw, mv, methodName);
                    right.emit(cw, mv, methodName);
                    mv.visitInsn(ISUB);
                }
                else if (left_exp.equals("Z") && right_exp.equals("F")) {
                    left.emit(cw, mv, methodName);
                    mv.visitInsn(I2F);
                    right.emit(cw, mv, methodName);
                    mv.visitInsn(FSUB);
                }
            }


            case MULTIPLY -> {
                if (left_exp.equals("I") && right_exp.equals("I")) {
                    left.emit(cw, mv, methodName);
                    right.emit(cw, mv, methodName);
                    mv.visitInsn(IMUL);
                }
                else if (left_exp.equals("I") && right_exp.equals("F")) {
                    left.emit(cw, mv, methodName);
                    mv.visitInsn(I2F);
                    right.emit(cw, mv, methodName);
                    mv.visitInsn(FMUL);
                }
                else if (left_exp.equals("I") && right_exp.equals("Z")) {
                    left.emit(cw, mv, methodName);
                    right.emit(cw, mv, methodName);
                    mv.visitInsn(IMUL);
                }
                else if (left_exp.equals("F") && right_exp.equals("F")) {
                    left.emit(cw, mv, methodName);
                    right.emit(cw, mv, methodName);
                    mv.visitInsn(FMUL);
                }
                else if (left_exp.equals("Z") && right_exp.equals("Z")) {
                    left.emit(cw, mv, methodName);
                    right.emit(cw, mv, methodName);
                    mv.visitInsn(IMUL);
                }
                else if (left_exp.equals("F") && right_exp.equals("I")) {
                    left.emit(cw, mv, methodName);
                    right.emit(cw, mv, methodName);
                    mv.visitInsn(I2F);
                    mv.visitInsn(FMUL);
                }
                else if (left_exp.equals("F") && right_exp.equals("Z")) {
                    left.emit(cw, mv, methodName);
                    right.emit(cw, mv, methodName);
                    mv.visitInsn(I2F);
                    mv.visitInsn(FMUL);
                }
                else if (left_exp.equals("Z") && right_exp.equals("I")) {
                    left.emit(cw, mv, methodName);
                    right.emit(cw, mv, methodName);
                    mv.visitInsn(IMUL);
                }
                else if (left_exp.equals("Z") && right_exp.equals("F")) {
                    left.emit(cw, mv, methodName);
                    mv.visitInsn(I2F);
                    right.emit(cw, mv, methodName);
                    mv.visitInsn(FMUL);
                }
            }

            case DIVIDE -> {
                if (left_exp.equals("I") && right_exp.equals("I")) {
                    left.emit(cw, mv, methodName);
                    right.emit(cw, mv, methodName);
                    mv.visitInsn(IDIV);
                }
                else if (left_exp.equals("I") && right_exp.equals("F")) {
                    left.emit(cw, mv, methodName);
                    mv.visitInsn(I2F);
                    right.emit(cw, mv, methodName);
                    mv.visitInsn(FDIV);
                }
                else if (left_exp.equals("I") && right_exp.equals("Z")) {
                    left.emit(cw, mv, methodName);
                    right.emit(cw, mv, methodName);
                    mv.visitInsn(IDIV);
                }
                else if (left_exp.equals("F") && right_exp.equals("F")) {
                    left.emit(cw, mv, methodName);
                    right.emit(cw, mv, methodName);
                    mv.visitInsn(FDIV);
                }
                else if (left_exp.equals("Z") && right_exp.equals("Z")) {
                    left.emit(cw, mv, methodName);
                    right.emit(cw, mv, methodName);
                    mv.visitInsn(IDIV);
                }
                else if (left_exp.equals("F") && right_exp.equals("I")) {
                    left.emit(cw, mv, methodName);
                    right.emit(cw, mv, methodName);
                    mv.visitInsn(I2F);
                    mv.visitInsn(FDIV);
                }
                else if (left_exp.equals("F") && right_exp.equals("Z")) {
                    left.emit(cw, mv, methodName);
                    right.emit(cw, mv, methodName);
                    mv.visitInsn(I2F);
                    mv.visitInsn(FDIV);
                }
                else if (left_exp.equals("Z") && right_exp.equals("I")) {
                    left.emit(cw, mv, methodName);
                    right.emit(cw, mv, methodName);
                    mv.visitInsn(IDIV);
                }
                else if (left_exp.equals("Z") && right_exp.equals("F")) {
                    left.emit(cw, mv, methodName);
                    mv.visitInsn(I2F);
                    right.emit(cw, mv, methodName);
                    mv.visitInsn(FDIV);
                }
            }

            case REMAINDER -> {
                if (left_exp.equals("I") && right_exp.equals("I")) {
                    left.emit(cw, mv, methodName);
                    right.emit(cw, mv, methodName);
                    mv.visitInsn(IREM);
                }
                else if (left_exp.equals("I") && right_exp.equals("F")) {
                    left.emit(cw, mv, methodName);
                    mv.visitInsn(I2F);
                    right.emit(cw, mv, methodName);
                    mv.visitInsn(FREM);
                }
                else if (left_exp.equals("I") && right_exp.equals("Z")) {
                    left.emit(cw, mv, methodName);
                    right.emit(cw, mv, methodName);
                    mv.visitInsn(IREM);
                }
                else if (left_exp.equals("F") && right_exp.equals("F")) {
                    left.emit(cw, mv, methodName);
                    right.emit(cw, mv, methodName);
                    mv.visitInsn(FREM);
                }
                else if (left_exp.equals("Z") && right_exp.equals("Z")) {
                    left.emit(cw, mv, methodName);
                    right.emit(cw, mv, methodName);
                    mv.visitInsn(IREM);
                }
                else if (left_exp.equals("F") && right_exp.equals("I")) {
                    left.emit(cw, mv, methodName);
                    right.emit(cw, mv, methodName);
                    mv.visitInsn(I2F);
                    mv.visitInsn(FREM);
                }
                else if (left_exp.equals("F") && right_exp.equals("Z")) {
                    left.emit(cw, mv, methodName);
                    right.emit(cw, mv, methodName);
                    mv.visitInsn(I2F);
                    mv.visitInsn(FREM);
                }
                else if (left_exp.equals("Z") && right_exp.equals("I")) {
                    left.emit(cw, mv, methodName);
                    right.emit(cw, mv, methodName);
                    mv.visitInsn(IREM);
                }
                else if (left_exp.equals("Z") && right_exp.equals("F")) {
                    left.emit(cw, mv, methodName);
                    mv.visitInsn(I2F);
                    right.emit(cw, mv, methodName);
                    mv.visitInsn(FREM);
                }
            }

            case AND -> {
                if (left_exp.equals("I") && right_exp.equals("I")) {
                    left.emit(cw, mv, methodName);
                    right.emit(cw, mv, methodName);
                    mv.visitInsn(IAND);
                }
                else if (left_exp.equals("I") && right_exp.equals("F")) {
                    throw new RuntimeException("Illegal expression: Bitwise operation does not accept real value");
                }
                else if (left_exp.equals("I") && right_exp.equals("Z")) {
                    left.emit(cw, mv, methodName);
                    right.emit(cw, mv, methodName);
                    mv.visitInsn(IAND);
                }
                else if (left_exp.equals("F") && right_exp.equals("F")) {
                    throw new RuntimeException("Illegal expression: Bitwise operation does not accept real value");
                }
                else if (left_exp.equals("Z") && right_exp.equals("Z")) {
                    left.emit(cw, mv, methodName);
                    right.emit(cw, mv, methodName);
                    mv.visitInsn(IAND);
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
                    mv.visitInsn(IAND);
                }
                else if (left_exp.equals("Z") && right_exp.equals("F")) {
                    throw new RuntimeException("Illegal expression: Bitwise operation does not accept real value");
                }
            }

            case OR -> {
                if (left_exp.equals("I") && right_exp.equals("I")) {
                    left.emit(cw, mv, methodName);
                    right.emit(cw, mv, methodName);
                    mv.visitInsn(IOR);
                }
                else if (left_exp.equals("I") && right_exp.equals("F")) {
                    throw new RuntimeException("Illegal expression: Bitwise operation does not accept real value");
                }
                else if (left_exp.equals("I") && right_exp.equals("Z")) {
                    left.emit(cw, mv, methodName);
                    right.emit(cw, mv, methodName);
                    mv.visitInsn(IOR);
                }
                else if (left_exp.equals("F") && right_exp.equals("F")) {
                    throw new RuntimeException("Illegal expression: Bitwise operation does not accept real value");
                }
                else if (left_exp.equals("Z") && right_exp.equals("Z")) {
                    left.emit(cw, mv, methodName);
                    right.emit(cw, mv, methodName);
                    mv.visitInsn(IOR);
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
                    mv.visitInsn(IOR);
                }
                else if (left_exp.equals("Z") && right_exp.equals("F")) {
                    throw new RuntimeException("Illegal expression: Bitwise operation does not accept real value");
                }
            }

            case XOR -> {
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

            case GREATER -> {
                if (left_exp.equals("I") && right_exp.equals("I")) {
                    Label False = new Label();
                    Label End = new Label();
                    left.emit(cw, mv, methodName);
                    right.emit(cw, mv, methodName);
                    mv.visitInsn(ISUB);
                    mv.visitInsn(ICONST_0);
                    mv.visitJumpInsn(IF_ICMPLE, False);
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
                    mv.visitJumpInsn(IF_ICMPGT, True);
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
                    mv.visitJumpInsn(IF_ICMPLE, False);
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
                    mv.visitJumpInsn(IF_ICMPGT, True);
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
                    mv.visitJumpInsn(IF_ICMPLE, False);
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
                    mv.visitJumpInsn(IF_ICMPGT, True);
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
                    mv.visitJumpInsn(IF_ICMPGT, True);
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
                    mv.visitJumpInsn(IF_ICMPLE, False);
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
                    mv.visitJumpInsn(IF_ICMPGT, True);
                    mv.visitInsn(FCONST_0);
                    mv.visitJumpInsn(GOTO, End);
                    mv.visitLabel(True);
                    mv.visitInsn(FCONST_1);
                    mv.visitLabel(End);
                }
            }

            case GEQUALS -> {
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

            case LESS -> {
                if (left_exp.equals("I") && right_exp.equals("I")) {
                    Label False = new Label();
                    Label End = new Label();
                    left.emit(cw, mv, methodName);
                    right.emit(cw, mv, methodName);
                    mv.visitInsn(ISUB);
                    mv.visitInsn(ICONST_0);
                    mv.visitJumpInsn(IF_ICMPGE, False);
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
                    mv.visitJumpInsn(IF_ICMPLT, True);
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
                    mv.visitJumpInsn(IF_ICMPGE, False);
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
                    mv.visitJumpInsn(IF_ICMPLT, True);
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
                    mv.visitJumpInsn(IF_ICMPGE, False);
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
                    mv.visitJumpInsn(IF_ICMPLT, True);
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
                    mv.visitJumpInsn(IF_ICMPLT, True);
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
                    mv.visitJumpInsn(IF_ICMPGE, False);
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
                    mv.visitJumpInsn(IF_ICMPLT, True);
                    mv.visitInsn(FCONST_0);
                    mv.visitJumpInsn(GOTO, End);
                    mv.visitLabel(True);
                    mv.visitInsn(FCONST_1);
                    mv.visitLabel(End);
                }
            }

            case LEQUALS -> {
                if (left_exp.equals("I") && right_exp.equals("I")) {
                    Label False = new Label();
                    Label End = new Label();
                    left.emit(cw, mv, methodName);
                    right.emit(cw, mv, methodName);
                    mv.visitInsn(ISUB);
                    mv.visitInsn(ICONST_0);
                    mv.visitJumpInsn(IF_ICMPGT, False);
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
                    mv.visitJumpInsn(IF_ICMPLE, True);
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
                    mv.visitJumpInsn(IF_ICMPGT, False);
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
                    mv.visitJumpInsn(IF_ICMPLE, True);
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
                    mv.visitJumpInsn(IF_ICMPGT, False);
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
                    mv.visitJumpInsn(IF_ICMPLE, True);
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
                    mv.visitJumpInsn(IF_ICMPLE, True);
                    mv.visitInsn(FCONST_0);
                    mv.visitJumpInsn(GOTO, End);
                    mv.visitLabel(True);
                    mv.visitInsn(FCONST_1);
                    mv.visitLabel(End);
                }
//
                else if (left_exp.equals("Z") && right_exp.equals("I")) {
                    Label False = new Label();
                    Label End = new Label();
                    left.emit(cw, mv, methodName);
                    right.emit(cw, mv, methodName);
                    mv.visitInsn(ISUB);
                    mv.visitInsn(ICONST_0);
                    mv.visitJumpInsn(IF_ICMPGT, False);
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
                    mv.visitJumpInsn(IF_ICMPLE, True);
                    mv.visitInsn(FCONST_0);
                    mv.visitJumpInsn(GOTO, End);
                    mv.visitLabel(True);
                    mv.visitInsn(FCONST_1);
                    mv.visitLabel(End);
                }
            }

            case NEQUALS -> {
                if (left_exp.equals("I") && right_exp.equals("I")) {
                    Label False = new Label();
                    Label End = new Label();
                    left.emit(cw, mv, methodName);
                    right.emit(cw, mv, methodName);
                    mv.visitInsn(ISUB);
                    mv.visitInsn(ICONST_0);
                    mv.visitJumpInsn(IF_ICMPEQ, False);
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
                    mv.visitJumpInsn(IF_ICMPEQ, True);
                    mv.visitInsn(FCONST_1);
                    mv.visitJumpInsn(GOTO, End);
                    mv.visitLabel(True);
                    mv.visitInsn(FCONST_0);
                    mv.visitLabel(End);
                }
                else if (left_exp.equals("I") && right_exp.equals("Z")) {
                    Label False = new Label();
                    Label End = new Label();
                    left.emit(cw, mv, methodName);
                    right.emit(cw, mv, methodName);
                    mv.visitInsn(ISUB);
                    mv.visitInsn(ICONST_0);
                    mv.visitJumpInsn(IF_ICMPEQ, False);
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
                    mv.visitJumpInsn(IF_ICMPEQ, True);
                    mv.visitInsn(FCONST_1);
                    mv.visitJumpInsn(GOTO, End);
                    mv.visitLabel(True);
                    mv.visitInsn(FCONST_0);
                    mv.visitLabel(End);
                }
                else if (left_exp.equals("Z") && right_exp.equals("Z")) {
                    Label False = new Label();
                    Label End = new Label();
                    left.emit(cw, mv, methodName);
                    right.emit(cw, mv, methodName);
                    mv.visitInsn(ISUB);
                    mv.visitInsn(ICONST_0);
                    mv.visitJumpInsn(IF_ICMPEQ, False);
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
                    mv.visitJumpInsn(IF_ICMPEQ, True);
                    mv.visitInsn(FCONST_1);
                    mv.visitJumpInsn(GOTO, End);
                    mv.visitLabel(True);
                    mv.visitInsn(FCONST_0);
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
                    mv.visitJumpInsn(IF_ICMPEQ, True);
                    mv.visitInsn(FCONST_1);
                    mv.visitJumpInsn(GOTO, End);
                    mv.visitLabel(True);
                    mv.visitInsn(FCONST_0);
                    mv.visitLabel(End);
                }
                else if (left_exp.equals("Z") && right_exp.equals("I")) {
                    Label False = new Label();
                    Label End = new Label();
                    left.emit(cw, mv, methodName);
                    right.emit(cw, mv, methodName);
                    mv.visitInsn(ISUB);
                    mv.visitInsn(ICONST_0);
                    mv.visitJumpInsn(IF_ICMPEQ, False);
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
                    mv.visitJumpInsn(IF_ICMPEQ, True);
                    mv.visitInsn(FCONST_1);
                    mv.visitJumpInsn(GOTO, End);
                    mv.visitLabel(True);
                    mv.visitInsn(FCONST_0);
                    mv.visitLabel(End);
                }
            }

            case EQUALS -> {
                if (left_exp.equals("I") && right_exp.equals("I")) {
                    Label False = new Label();
                    Label End = new Label();
                    left.emit(cw, mv, methodName);
                    right.emit(cw, mv, methodName);
                    mv.visitInsn(ISUB);
                    mv.visitInsn(ICONST_0);
                    mv.visitJumpInsn(IF_ICMPNE, False);
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
                    mv.visitJumpInsn(IF_ICMPEQ, True);
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
                    mv.visitJumpInsn(IF_ICMPNE, False);
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
                    mv.visitJumpInsn(IF_ICMPEQ, True);
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
                    mv.visitJumpInsn(IF_ICMPNE, False);
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
                    mv.visitJumpInsn(IF_ICMPEQ, True);
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
                    mv.visitJumpInsn(IF_ICMPEQ, True);
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
                    mv.visitJumpInsn(IF_ICMPNE, False);
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
                    mv.visitJumpInsn(IF_ICMPEQ, True);
                    mv.visitInsn(FCONST_0);
                    mv.visitJumpInsn(GOTO, End);
                    mv.visitLabel(True);
                    mv.visitInsn(FCONST_1);
                    mv.visitLabel(End);
                }
            }


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

        if (left_exp.equals("I") && right_exp.equals("I")) {
            return "I";
        }
        else if (left_exp.equals("I") && right_exp.equals("F")) {
            return "F";
        }
        else if (left_exp.equals("I") && right_exp.equals("Z")) {
            return "I";
        }
        else if (left_exp.equals("F") && right_exp.equals("F")) {
            return "F";
        }
        else if (left_exp.equals("Z") && right_exp.equals("Z")) {
            return "Z";
        }
        else if (left_exp.equals("F") && right_exp.equals("I")) {
            return "F";
        }
        else if (left_exp.equals("F") && right_exp.equals("Z")) {
            return "F";
        }
        else if (left_exp.equals("Z") && right_exp.equals("I")) {
            return "I";
        }
        else if (left_exp.equals("Z") && right_exp.equals("F")) {
            return "F";
        }
        else {
            System.out.println("Something wrong with types");
            return null;
        }

    }
}
