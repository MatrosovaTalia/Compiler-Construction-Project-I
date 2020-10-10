package simple;

import misc.Pair;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;


import java.util.ArrayList;
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
        String[] types = new String[] {"I", "D", "Z"};
        result_type.put(new Triple("I", "I", Operation.PLUS), IADD);
        result_type.put(new Triple("I", "D", Operation.PLUS), IADD);
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
        String left_exp = left.resolve_type();
        String right_exp = right.resolve_type();

        switch (operation) {
            case PLUS -> {
                if (left_exp.equals("I") && right_exp.equals("I")) {
                    left.emit(cw, mv);
                    right.emit(cw, mv);
                    mv.visitInsn(IADD);
                }
                else if (left_exp.equals("I") && right_exp.equals("D")) {
                    left.emit(cw, mv);
                    mv.visitInsn(I2D);
                    right.emit(cw, mv);
                    mv.visitInsn(DADD);
                }
                else if (left_exp.equals("I") && right_exp.equals("Z")) {
                    left.emit(cw, mv);
                    right.emit(cw, mv);
                    mv.visitInsn(IADD);
                }
                else if (left_exp.equals("D") && right_exp.equals("D")) {
                    left.emit(cw, mv);
                    right.emit(cw, mv);
                    mv.visitInsn(DADD);
                }
                else if (left_exp.equals("Z") && right_exp.equals("Z")) {
                    left.emit(cw, mv);
                    right.emit(cw, mv);
                    mv.visitInsn(IADD);
                }
                else if (left_exp.equals("D") && right_exp.equals("I")) {
                    left.emit(cw, mv);
                    right.emit(cw, mv);
                    mv.visitInsn(I2D);
                    mv.visitInsn(DADD);
                }
                else if (left_exp.equals("D") && right_exp.equals("Z")) {
                    left.emit(cw, mv);
                    right.emit(cw, mv);
                    mv.visitInsn(I2D);
                    mv.visitInsn(DADD);
                }
                else if (left_exp.equals("Z") && right_exp.equals("I")) {
                    left.emit(cw, mv);
                    right.emit(cw, mv);
                    mv.visitInsn(IADD);
                }
                else if (left_exp.equals("Z") && right_exp.equals("D")) {
                    left.emit(cw, mv);
                    mv.visitInsn(I2D);
                    right.emit(cw, mv);
                    mv.visitInsn(DADD);
                }
            }

            case MINUS -> {
                if (left_exp.equals("I") && right_exp.equals("I")) {
                    left.emit(cw, mv);
                    right.emit(cw, mv);
                    mv.visitInsn(ISUB);
                }
                else if (left_exp.equals("I") && right_exp.equals("D")) {
                    left.emit(cw, mv);
                    mv.visitInsn(I2D);
                    right.emit(cw, mv);
                    mv.visitInsn(DSUB);
                }
                else if (left_exp.equals("I") && right_exp.equals("Z")) {
                    left.emit(cw, mv);
                    right.emit(cw, mv);
                    mv.visitInsn(ISUB);
                }
                else if (left_exp.equals("D") && right_exp.equals("D")) {
                    left.emit(cw, mv);
                    right.emit(cw, mv);
                    mv.visitInsn(DSUB);
                }
                else if (left_exp.equals("Z") && right_exp.equals("Z")) {
                    left.emit(cw, mv);
                    right.emit(cw, mv);
                    mv.visitInsn(ISUB);
                }
                else if (left_exp.equals("D") && right_exp.equals("I")) {
                    left.emit(cw, mv);
                    right.emit(cw, mv);
                    mv.visitInsn(I2D);
                    mv.visitInsn(DSUB);
                }
                else if (left_exp.equals("D") && right_exp.equals("Z")) {
                    left.emit(cw, mv);
                    right.emit(cw, mv);
                    mv.visitInsn(I2D);
                    mv.visitInsn(DSUB);
                }
                else if (left_exp.equals("Z") && right_exp.equals("I")) {
                    left.emit(cw, mv);
                    right.emit(cw, mv);
                    mv.visitInsn(ISUB);
                }
                else if (left_exp.equals("Z") && right_exp.equals("D")) {
                    left.emit(cw, mv);
                    mv.visitInsn(I2D);
                    right.emit(cw, mv);
                    mv.visitInsn(DSUB);
                }
            }


            case MULTIPLY -> {
                if (left_exp.equals("I") && right_exp.equals("I")) {
                    left.emit(cw, mv);
                    right.emit(cw, mv);
                    mv.visitInsn(IMUL);
                }
                else if (left_exp.equals("I") && right_exp.equals("D")) {
                    left.emit(cw, mv);
                    mv.visitInsn(I2D);
                    right.emit(cw, mv);
                    mv.visitInsn(DMUL);
                }
                else if (left_exp.equals("I") && right_exp.equals("Z")) {
                    left.emit(cw, mv);
                    right.emit(cw, mv);
                    mv.visitInsn(IMUL);
                }
                else if (left_exp.equals("D") && right_exp.equals("D")) {
                    left.emit(cw, mv);
                    right.emit(cw, mv);
                    mv.visitInsn(DMUL);
                }
                else if (left_exp.equals("Z") && right_exp.equals("Z")) {
                    left.emit(cw, mv);
                    right.emit(cw, mv);
                    mv.visitInsn(IMUL);
                }
                else if (left_exp.equals("D") && right_exp.equals("I")) {
                    left.emit(cw, mv);
                    right.emit(cw, mv);
                    mv.visitInsn(I2D);
                    mv.visitInsn(DMUL);
                }
                else if (left_exp.equals("D") && right_exp.equals("Z")) {
                    left.emit(cw, mv);
                    right.emit(cw, mv);
                    mv.visitInsn(I2D);
                    mv.visitInsn(DMUL);
                }
                else if (left_exp.equals("Z") && right_exp.equals("I")) {
                    left.emit(cw, mv);
                    right.emit(cw, mv);
                    mv.visitInsn(IMUL);
                }
                else if (left_exp.equals("Z") && right_exp.equals("D")) {
                    left.emit(cw, mv);
                    mv.visitInsn(I2D);
                    right.emit(cw, mv);
                    mv.visitInsn(DMUL);
                }
            }

            case DIVIDE -> {
                if (left_exp.equals("I") && right_exp.equals("I")) {
                    left.emit(cw, mv);
                    right.emit(cw, mv);
                    mv.visitInsn(IDIV);
                }
                else if (left_exp.equals("I") && right_exp.equals("D")) {
                    left.emit(cw, mv);
                    mv.visitInsn(I2D);
                    right.emit(cw, mv);
                    mv.visitInsn(DDIV);
                }
                else if (left_exp.equals("I") && right_exp.equals("Z")) {
                    left.emit(cw, mv);
                    right.emit(cw, mv);
                    mv.visitInsn(IDIV);
                }
                else if (left_exp.equals("D") && right_exp.equals("D")) {
                    left.emit(cw, mv);
                    right.emit(cw, mv);
                    mv.visitInsn(DDIV);
                }
                else if (left_exp.equals("Z") && right_exp.equals("Z")) {
                    left.emit(cw, mv);
                    right.emit(cw, mv);
                    mv.visitInsn(IDIV);
                }
                else if (left_exp.equals("D") && right_exp.equals("I")) {
                    left.emit(cw, mv);
                    right.emit(cw, mv);
                    mv.visitInsn(I2D);
                    mv.visitInsn(DDIV);
                }
                else if (left_exp.equals("D") && right_exp.equals("Z")) {
                    left.emit(cw, mv);
                    right.emit(cw, mv);
                    mv.visitInsn(I2D);
                    mv.visitInsn(DDIV);
                }
                else if (left_exp.equals("Z") && right_exp.equals("I")) {
                    left.emit(cw, mv);
                    right.emit(cw, mv);
                    mv.visitInsn(IDIV);
                }
                else if (left_exp.equals("Z") && right_exp.equals("D")) {
                    left.emit(cw, mv);
                    mv.visitInsn(I2D);
                    right.emit(cw, mv);
                    mv.visitInsn(DDIV);
                }
            }

            case REMAINDER -> {
                if (left_exp.equals("I") && right_exp.equals("I")) {
                    left.emit(cw, mv);
                    right.emit(cw, mv);
                    mv.visitInsn(IREM);
                }
                else if (left_exp.equals("I") && right_exp.equals("D")) {
                    left.emit(cw, mv);
                    mv.visitInsn(I2D);
                    right.emit(cw, mv);
                    mv.visitInsn(DREM);
                }
                else if (left_exp.equals("I") && right_exp.equals("Z")) {
                    left.emit(cw, mv);
                    right.emit(cw, mv);
                    mv.visitInsn(IREM);
                }
                else if (left_exp.equals("D") && right_exp.equals("D")) {
                    left.emit(cw, mv);
                    right.emit(cw, mv);
                    mv.visitInsn(DREM);
                }
                else if (left_exp.equals("Z") && right_exp.equals("Z")) {
                    left.emit(cw, mv);
                    right.emit(cw, mv);
                    mv.visitInsn(IREM);
                }
                else if (left_exp.equals("D") && right_exp.equals("I")) {
                    left.emit(cw, mv);
                    right.emit(cw, mv);
                    mv.visitInsn(I2D);
                    mv.visitInsn(DREM);
                }
                else if (left_exp.equals("D") && right_exp.equals("Z")) {
                    left.emit(cw, mv);
                    right.emit(cw, mv);
                    mv.visitInsn(I2D);
                    mv.visitInsn(DREM);
                }
                else if (left_exp.equals("Z") && right_exp.equals("I")) {
                    left.emit(cw, mv);
                    right.emit(cw, mv);
                    mv.visitInsn(IREM);
                }
                else if (left_exp.equals("Z") && right_exp.equals("D")) {
                    left.emit(cw, mv);
                    mv.visitInsn(I2D);
                    right.emit(cw, mv);
                    mv.visitInsn(DREM);
                }
            }

            case AND -> {
                if (left_exp.equals("I") && right_exp.equals("I")) {
                    left.emit(cw, mv);
                    right.emit(cw, mv);
                    mv.visitInsn(IAND);
                }
                else if (left_exp.equals("I") && right_exp.equals("D")) {
                    throw new RuntimeException("Illegal expression: Bitwise operation does not accept real value");
                }
                else if (left_exp.equals("I") && right_exp.equals("Z")) {
                    left.emit(cw, mv);
                    right.emit(cw, mv);
                    mv.visitInsn(IAND);
                }
                else if (left_exp.equals("D") && right_exp.equals("D")) {
                    throw new RuntimeException("Illegal expression: Bitwise operation does not accept real value");
                }
                else if (left_exp.equals("Z") && right_exp.equals("Z")) {
                    left.emit(cw, mv);
                    right.emit(cw, mv);
                    mv.visitInsn(IAND);
                }
                else if (left_exp.equals("D") && right_exp.equals("I")) {
                    throw new RuntimeException("Illegal expression: Bitwise operation does not accept real value");
                }
                else if (left_exp.equals("D") && right_exp.equals("Z")) {
                    throw new RuntimeException("Illegal expression: Bitwise operation does not accept real value");
                }
                else if (left_exp.equals("Z") && right_exp.equals("I")) {
                    left.emit(cw, mv);
                    right.emit(cw, mv);
                    mv.visitInsn(IAND);
                }
                else if (left_exp.equals("Z") && right_exp.equals("D")) {
                    throw new RuntimeException("Illegal expression: Bitwise operation does not accept real value");
                }
            }

            case OR -> {
                if (left_exp.equals("I") && right_exp.equals("I")) {
                    left.emit(cw, mv);
                    right.emit(cw, mv);
                    mv.visitInsn(IOR);
                }
                else if (left_exp.equals("I") && right_exp.equals("D")) {
                    throw new RuntimeException("Illegal expression: Bitwise operation does not accept real value");
                }
                else if (left_exp.equals("I") && right_exp.equals("Z")) {
                    left.emit(cw, mv);
                    right.emit(cw, mv);
                    mv.visitInsn(IOR);
                }
                else if (left_exp.equals("D") && right_exp.equals("D")) {
                    throw new RuntimeException("Illegal expression: Bitwise operation does not accept real value");
                }
                else if (left_exp.equals("Z") && right_exp.equals("Z")) {
                    left.emit(cw, mv);
                    right.emit(cw, mv);
                    mv.visitInsn(IOR);
                }
                else if (left_exp.equals("D") && right_exp.equals("I")) {
                    throw new RuntimeException("Illegal expression: Bitwise operation does not accept real value");
                }
                else if (left_exp.equals("D") && right_exp.equals("Z")) {
                    throw new RuntimeException("Illegal expression: Bitwise operation does not accept real value");
                }
                else if (left_exp.equals("Z") && right_exp.equals("I")) {
                    left.emit(cw, mv);
                    right.emit(cw, mv);
                    mv.visitInsn(IOR);
                }
                else if (left_exp.equals("Z") && right_exp.equals("D")) {
                    throw new RuntimeException("Illegal expression: Bitwise operation does not accept real value");
                }
            }

            case XOR -> {
                if (left_exp.equals("I") && right_exp.equals("I")) {
                    left.emit(cw, mv);
                    right.emit(cw, mv);
                    mv.visitInsn(IXOR);
                }
                else if (left_exp.equals("I") && right_exp.equals("D")) {
                    throw new RuntimeException("Illegal expression: Bitwise operation does not accept real value");
                }
                else if (left_exp.equals("I") && right_exp.equals("Z")) {
                    left.emit(cw, mv);
                    right.emit(cw, mv);
                    mv.visitInsn(IXOR);
                }
                else if (left_exp.equals("D") && right_exp.equals("D")) {
                    throw new RuntimeException("Illegal expression: Bitwise operation does not accept real value");
                }
                else if (left_exp.equals("Z") && right_exp.equals("Z")) {
                    left.emit(cw, mv);
                    right.emit(cw, mv);
                    mv.visitInsn(IXOR);
                }
                else if (left_exp.equals("D") && right_exp.equals("I")) {
                    throw new RuntimeException("Illegal expression: Bitwise operation does not accept real value");
                }
                else if (left_exp.equals("D") && right_exp.equals("Z")) {
                    throw new RuntimeException("Illegal expression: Bitwise operation does not accept real value");
                }
                else if (left_exp.equals("Z") && right_exp.equals("I")) {
                    left.emit(cw, mv);
                    right.emit(cw, mv);
                    mv.visitInsn(IXOR);
                }
                else if (left_exp.equals("Z") && right_exp.equals("D")) {
                    throw new RuntimeException("Illegal expression: Bitwise operation does not accept real value");
                }
            }

            case GREATER -> {
                if (left_exp.equals("I") && right_exp.equals("I")) {
                    Label False = new Label();
                    Label End = new Label();
                    left.emit(cw, mv);
                    right.emit(cw, mv);
                    mv.visitInsn(ISUB);
                    mv.visitInsn(ICONST_0);
                    mv.visitJumpInsn(IF_ICMPLE, False);
                    mv.visitInsn(ICONST_1);
                    mv.visitJumpInsn(GOTO, End);
                    mv.visitLabel(False);
                    mv.visitInsn(ICONST_0);
                    mv.visitLabel(End);
                }
                else if (left_exp.equals("I") && right_exp.equals("D")) {
                    Label True = new Label();
                    Label End = new Label();
                    left.emit(cw, mv);
                    mv.visitInsn(I2D);
                    right.emit(cw, mv);
                    mv.visitInsn(DCMPG);
                    mv.visitInsn(ICONST_0);
                    mv.visitJumpInsn(IF_ICMPGT, True);
                    mv.visitInsn(DCONST_0);
                    mv.visitJumpInsn(GOTO, End);
                    mv.visitLabel(True);
                    mv.visitInsn(DCONST_1);
                    mv.visitLabel(End);
                }
                else if (left_exp.equals("I") && right_exp.equals("Z")) {
                    Label False = new Label();
                    Label End = new Label();
                    left.emit(cw, mv);
                    right.emit(cw, mv);
                    mv.visitInsn(ISUB);
                    mv.visitInsn(ICONST_0);
                    mv.visitJumpInsn(IF_ICMPLE, False);
                    mv.visitInsn(ICONST_1);
                    mv.visitJumpInsn(GOTO, End);
                    mv.visitLabel(False);
                    mv.visitInsn(ICONST_0);
                    mv.visitLabel(End);
                }
                else if (left_exp.equals("D") && right_exp.equals("D")) {
                    Label True = new Label();
                    Label End = new Label();
                    left.emit(cw, mv);
                    right.emit(cw, mv);
                    mv.visitInsn(DCMPG);
                    mv.visitInsn(ICONST_0);
                    mv.visitJumpInsn(IF_ICMPGT, True);
                    mv.visitInsn(DCONST_0);
                    mv.visitJumpInsn(GOTO, End);
                    mv.visitLabel(True);
                    mv.visitInsn(DCONST_1);
                    mv.visitLabel(End);
                }
                else if (left_exp.equals("Z") && right_exp.equals("Z")) {
                    Label False = new Label();
                    Label End = new Label();
                    left.emit(cw, mv);
                    right.emit(cw, mv);
                    mv.visitInsn(ISUB);
                    mv.visitInsn(ICONST_0);
                    mv.visitJumpInsn(IF_ICMPLE, False);
                    mv.visitInsn(ICONST_1);
                    mv.visitJumpInsn(GOTO, End);
                    mv.visitLabel(False);
                    mv.visitInsn(ICONST_0);
                    mv.visitLabel(End);
                }
                else if (left_exp.equals("D") && right_exp.equals("I")) {
                    Label True = new Label();
                    Label End = new Label();
                    left.emit(cw, mv);
                    right.emit(cw, mv);
                    mv.visitInsn(I2D);
                    mv.visitInsn(DCMPG);
                    mv.visitInsn(ICONST_0);
                    mv.visitJumpInsn(IF_ICMPGT, True);
                    mv.visitInsn(DCONST_0);
                    mv.visitJumpInsn(GOTO, End);
                    mv.visitLabel(True);
                    mv.visitInsn(DCONST_1);
                    mv.visitLabel(End);
                }
                else if (left_exp.equals("D") && right_exp.equals("Z")) {
                    Label True = new Label();
                    Label End = new Label();
                    left.emit(cw, mv);
                    right.emit(cw, mv);
                    mv.visitInsn(I2D);
                    mv.visitInsn(DCMPG);
                    mv.visitInsn(ICONST_0);
                    mv.visitJumpInsn(IF_ICMPGT, True);
                    mv.visitInsn(DCONST_0);
                    mv.visitJumpInsn(GOTO, End);
                    mv.visitLabel(True);
                    mv.visitInsn(DCONST_1);
                    mv.visitLabel(End);
                }
                else if (left_exp.equals("Z") && right_exp.equals("I")) {
                    Label False = new Label();
                    Label End = new Label();
                    left.emit(cw, mv);
                    right.emit(cw, mv);
                    mv.visitInsn(ISUB);
                    mv.visitInsn(ICONST_0);
                    mv.visitJumpInsn(IF_ICMPLE, False);
                    mv.visitInsn(ICONST_1);
                    mv.visitJumpInsn(GOTO, End);
                    mv.visitLabel(False);
                    mv.visitInsn(ICONST_0);
                    mv.visitLabel(End);
                }
                else if (left_exp.equals("Z") && right_exp.equals("D")) {
                    Label True = new Label();
                    Label End = new Label();
                    left.emit(cw, mv);
                    mv.visitInsn(I2D);
                    right.emit(cw, mv);
                    mv.visitInsn(DCMPG);
                    mv.visitInsn(ICONST_0);
                    mv.visitJumpInsn(IF_ICMPGT, True);
                    mv.visitInsn(DCONST_0);
                    mv.visitJumpInsn(GOTO, End);
                    mv.visitLabel(True);
                    mv.visitInsn(DCONST_1);
                    mv.visitLabel(End);
                }
            }

            case GEQUALS -> {
                if (left_exp.equals("I") && right_exp.equals("I")) {
                    Label False = new Label();
                    Label End = new Label();
                    left.emit(cw, mv);
                    right.emit(cw, mv);
                    mv.visitInsn(ISUB);
                    mv.visitInsn(ICONST_0);
                    mv.visitJumpInsn(IF_ICMPLT, False);
                    mv.visitInsn(ICONST_1);
                    mv.visitJumpInsn(GOTO, End);
                    mv.visitLabel(False);
                    mv.visitInsn(ICONST_0);
                    mv.visitLabel(End);
                }
                else if (left_exp.equals("I") && right_exp.equals("D")) {
                    Label True = new Label();
                    Label End = new Label();
                    left.emit(cw, mv);
                    mv.visitInsn(I2D);
                    right.emit(cw, mv);
                    mv.visitInsn(DCMPG);
                    mv.visitInsn(ICONST_0);
                    mv.visitJumpInsn(IF_ICMPGE, True);
                    mv.visitInsn(DCONST_0);
                    mv.visitJumpInsn(GOTO, End);
                    mv.visitLabel(True);
                    mv.visitInsn(DCONST_1);
                    mv.visitLabel(End);
                }
                else if (left_exp.equals("I") && right_exp.equals("Z")) {
                    Label False = new Label();
                    Label End = new Label();
                    left.emit(cw, mv);
                    right.emit(cw, mv);
                    mv.visitInsn(ISUB);
                    mv.visitInsn(ICONST_0);
                    mv.visitJumpInsn(IF_ICMPLT, False);
                    mv.visitInsn(ICONST_1);
                    mv.visitJumpInsn(GOTO, End);
                    mv.visitLabel(False);
                    mv.visitInsn(ICONST_0);
                    mv.visitLabel(End);
                }
                else if (left_exp.equals("D") && right_exp.equals("D")) {
                    Label True = new Label();
                    Label End = new Label();
                    left.emit(cw, mv);
                    right.emit(cw, mv);
                    mv.visitInsn(DCMPG);
                    mv.visitInsn(ICONST_0);
                    mv.visitJumpInsn(IF_ICMPGE, True);
                    mv.visitInsn(DCONST_0);
                    mv.visitJumpInsn(GOTO, End);
                    mv.visitLabel(True);
                    mv.visitInsn(DCONST_1);
                    mv.visitLabel(End);
                }
                else if (left_exp.equals("Z") && right_exp.equals("Z")) {
                    Label False = new Label();
                    Label End = new Label();
                    left.emit(cw, mv);
                    right.emit(cw, mv);
                    mv.visitInsn(ISUB);
                    mv.visitInsn(ICONST_0);
                    mv.visitJumpInsn(IF_ICMPLT, False);
                    mv.visitInsn(ICONST_1);
                    mv.visitJumpInsn(GOTO, End);
                    mv.visitLabel(False);
                    mv.visitInsn(ICONST_0);
                    mv.visitLabel(End);
                }
                else if (left_exp.equals("D") && right_exp.equals("I")) {
                    Label True = new Label();
                    Label End = new Label();
                    left.emit(cw, mv);
                    right.emit(cw, mv);
                    mv.visitInsn(I2D);
                    mv.visitInsn(DCMPG);
                    mv.visitInsn(ICONST_0);
                    mv.visitJumpInsn(IF_ICMPGE, True);
                    mv.visitInsn(DCONST_0);
                    mv.visitJumpInsn(GOTO, End);
                    mv.visitLabel(True);
                    mv.visitInsn(DCONST_1);
                    mv.visitLabel(End);
                }
                else if (left_exp.equals("D") && right_exp.equals("Z")) {
                    Label True = new Label();
                    Label End = new Label();
                    left.emit(cw, mv);
                    right.emit(cw, mv);
                    mv.visitInsn(I2D);
                    mv.visitInsn(DCMPG);
                    mv.visitInsn(ICONST_0);
                    mv.visitJumpInsn(IF_ICMPGE, True);
                    mv.visitInsn(DCONST_0);
                    mv.visitJumpInsn(GOTO, End);
                    mv.visitLabel(True);
                    mv.visitInsn(DCONST_1);
                    mv.visitLabel(End);
                }
                else if (left_exp.equals("Z") && right_exp.equals("I")) {
                    Label False = new Label();
                    Label End = new Label();
                    left.emit(cw, mv);
                    right.emit(cw, mv);
                    mv.visitInsn(ISUB);
                    mv.visitInsn(ICONST_0);
                    mv.visitJumpInsn(IF_ICMPLT, False);
                    mv.visitInsn(ICONST_1);
                    mv.visitJumpInsn(GOTO, End);
                    mv.visitLabel(False);
                    mv.visitInsn(ICONST_0);
                    mv.visitLabel(End);
                }
                else if (left_exp.equals("Z") && right_exp.equals("D")) {
                    Label True = new Label();
                    Label End = new Label();
                    left.emit(cw, mv);
                    mv.visitInsn(I2D);
                    right.emit(cw, mv);
                    mv.visitInsn(DCMPG);
                    mv.visitInsn(ICONST_0);
                    mv.visitJumpInsn(IF_ICMPGE, True);
                    mv.visitInsn(DCONST_0);
                    mv.visitJumpInsn(GOTO, End);
                    mv.visitLabel(True);
                    mv.visitInsn(DCONST_1);
                    mv.visitLabel(End);
                }
            }

            case LESS -> {
                if (left_exp.equals("I") && right_exp.equals("I")) {
                    Label False = new Label();
                    Label End = new Label();
                    left.emit(cw, mv);
                    right.emit(cw, mv);
                    mv.visitInsn(ISUB);
                    mv.visitInsn(ICONST_0);
                    mv.visitJumpInsn(IF_ICMPGE, False);
                    mv.visitInsn(ICONST_1);
                    mv.visitJumpInsn(GOTO, End);
                    mv.visitLabel(False);
                    mv.visitInsn(ICONST_0);
                    mv.visitLabel(End);
                }
                else if (left_exp.equals("I") && right_exp.equals("D")) {
                    Label True = new Label();
                    Label End = new Label();
                    left.emit(cw, mv);
                    mv.visitInsn(I2D);
                    right.emit(cw, mv);
                    mv.visitInsn(DCMPG);
                    mv.visitInsn(ICONST_0);
                    mv.visitJumpInsn(IF_ICMPLT, True);
                    mv.visitInsn(DCONST_0);
                    mv.visitJumpInsn(GOTO, End);
                    mv.visitLabel(True);
                    mv.visitInsn(DCONST_1);
                    mv.visitLabel(End);
                }
                else if (left_exp.equals("I") && right_exp.equals("Z")) {
                    Label False = new Label();
                    Label End = new Label();
                    left.emit(cw, mv);
                    right.emit(cw, mv);
                    mv.visitInsn(ISUB);
                    mv.visitInsn(ICONST_0);
                    mv.visitJumpInsn(IF_ICMPGE, False);
                    mv.visitInsn(ICONST_1);
                    mv.visitJumpInsn(GOTO, End);
                    mv.visitLabel(False);
                    mv.visitInsn(ICONST_0);
                    mv.visitLabel(End);
                }
                else if (left_exp.equals("D") && right_exp.equals("D")) {
                    Label True = new Label();
                    Label End = new Label();
                    left.emit(cw, mv);
                    right.emit(cw, mv);
                    mv.visitInsn(DCMPG);
                    mv.visitInsn(ICONST_0);
                    mv.visitJumpInsn(IF_ICMPLT, True);
                    mv.visitInsn(DCONST_0);
                    mv.visitJumpInsn(GOTO, End);
                    mv.visitLabel(True);
                    mv.visitInsn(DCONST_1);
                    mv.visitLabel(End);
                }
                else if (left_exp.equals("Z") && right_exp.equals("Z")) {
                    Label False = new Label();
                    Label End = new Label();
                    left.emit(cw, mv);
                    right.emit(cw, mv);
                    mv.visitInsn(ISUB);
                    mv.visitInsn(ICONST_0);
                    mv.visitJumpInsn(IF_ICMPGE, False);
                    mv.visitInsn(ICONST_1);
                    mv.visitJumpInsn(GOTO, End);
                    mv.visitLabel(False);
                    mv.visitInsn(ICONST_0);
                    mv.visitLabel(End);
                }
                else if (left_exp.equals("D") && right_exp.equals("I")) {
                    Label True = new Label();
                    Label End = new Label();
                    left.emit(cw, mv);
                    right.emit(cw, mv);
                    mv.visitInsn(I2D);
                    mv.visitInsn(DCMPG);
                    mv.visitInsn(ICONST_0);
                    mv.visitJumpInsn(IF_ICMPLT, True);
                    mv.visitInsn(DCONST_0);
                    mv.visitJumpInsn(GOTO, End);
                    mv.visitLabel(True);
                    mv.visitInsn(DCONST_1);
                    mv.visitLabel(End);
                }
                else if (left_exp.equals("D") && right_exp.equals("Z")) {
                    Label True = new Label();
                    Label End = new Label();
                    left.emit(cw, mv);
                    right.emit(cw, mv);
                    mv.visitInsn(I2D);
                    mv.visitInsn(DCMPG);
                    mv.visitInsn(ICONST_0);
                    mv.visitJumpInsn(IF_ICMPLT, True);
                    mv.visitInsn(DCONST_0);
                    mv.visitJumpInsn(GOTO, End);
                    mv.visitLabel(True);
                    mv.visitInsn(DCONST_1);
                    mv.visitLabel(End);
                }
                else if (left_exp.equals("Z") && right_exp.equals("I")) {
                    Label False = new Label();
                    Label End = new Label();
                    left.emit(cw, mv);
                    right.emit(cw, mv);
                    mv.visitInsn(ISUB);
                    mv.visitInsn(ICONST_0);
                    mv.visitJumpInsn(IF_ICMPGE, False);
                    mv.visitInsn(ICONST_1);
                    mv.visitJumpInsn(GOTO, End);
                    mv.visitLabel(False);
                    mv.visitInsn(ICONST_0);
                    mv.visitLabel(End);
                }
                else if (left_exp.equals("Z") && right_exp.equals("D")) {
                    Label True = new Label();
                    Label End = new Label();
                    left.emit(cw, mv);
                    mv.visitInsn(I2D);
                    right.emit(cw, mv);
                    mv.visitInsn(DCMPG);
                    mv.visitInsn(ICONST_0);
                    mv.visitJumpInsn(IF_ICMPLT, True);
                    mv.visitInsn(DCONST_0);
                    mv.visitJumpInsn(GOTO, End);
                    mv.visitLabel(True);
                    mv.visitInsn(DCONST_1);
                    mv.visitLabel(End);
                }
            }

            case LEQUALS -> {
                if (left_exp.equals("I") && right_exp.equals("I")) {
                    Label False = new Label();
                    Label End = new Label();
                    left.emit(cw, mv);
                    right.emit(cw, mv);
                    mv.visitInsn(ISUB);
                    mv.visitInsn(ICONST_0);
                    mv.visitJumpInsn(IF_ICMPGT, False);
                    mv.visitInsn(ICONST_1);
                    mv.visitJumpInsn(GOTO, End);
                    mv.visitLabel(False);
                    mv.visitInsn(ICONST_0);
                    mv.visitLabel(End);
                }

                else if (left_exp.equals("I") && right_exp.equals("D")) {
                    Label True = new Label();
                    Label End = new Label();
                    left.emit(cw, mv);
                    mv.visitInsn(I2D);
                    right.emit(cw, mv);
                    mv.visitInsn(DCMPG);
                    mv.visitInsn(ICONST_0);
                    mv.visitJumpInsn(IF_ICMPLE, True);
                    mv.visitInsn(DCONST_0);
                    mv.visitJumpInsn(GOTO, End);
                    mv.visitLabel(True);
                    mv.visitInsn(DCONST_1);
                    mv.visitLabel(End);
                }
                else if (left_exp.equals("I") && right_exp.equals("Z")) {
                    Label False = new Label();
                    Label End = new Label();
                    left.emit(cw, mv);
                    right.emit(cw, mv);
                    mv.visitInsn(ISUB);
                    mv.visitInsn(ICONST_0);
                    mv.visitJumpInsn(IF_ICMPGT, False);
                    mv.visitInsn(ICONST_1);
                    mv.visitJumpInsn(GOTO, End);
                    mv.visitLabel(False);
                    mv.visitInsn(ICONST_0);
                    mv.visitLabel(End);
                }
                else if (left_exp.equals("D") && right_exp.equals("D")) {
                    Label True = new Label();
                    Label End = new Label();
                    left.emit(cw, mv);
                    right.emit(cw, mv);
                    mv.visitInsn(DCMPG);
                    mv.visitInsn(ICONST_0);
                    mv.visitJumpInsn(IF_ICMPLE, True);
                    mv.visitInsn(DCONST_0);
                    mv.visitJumpInsn(GOTO, End);
                    mv.visitLabel(True);
                    mv.visitInsn(DCONST_1);
                    mv.visitLabel(End);
                }
                else if (left_exp.equals("Z") && right_exp.equals("Z")) {
                    Label False = new Label();
                    Label End = new Label();
                    left.emit(cw, mv);
                    right.emit(cw, mv);
                    mv.visitInsn(ISUB);
                    mv.visitInsn(ICONST_0);
                    mv.visitJumpInsn(IF_ICMPGT, False);
                    mv.visitInsn(ICONST_1);
                    mv.visitJumpInsn(GOTO, End);
                    mv.visitLabel(False);
                    mv.visitInsn(ICONST_0);
                    mv.visitLabel(End);
                }
                else if (left_exp.equals("D") && right_exp.equals("I")) {
                    Label True = new Label();
                    Label End = new Label();
                    left.emit(cw, mv);
                    right.emit(cw, mv);
                    mv.visitInsn(I2D);
                    mv.visitInsn(DCMPG);
                    mv.visitInsn(ICONST_0);
                    mv.visitJumpInsn(IF_ICMPLE, True);
                    mv.visitInsn(DCONST_0);
                    mv.visitJumpInsn(GOTO, End);
                    mv.visitLabel(True);
                    mv.visitInsn(DCONST_1);
                    mv.visitLabel(End);
                }
                else if (left_exp.equals("D") && right_exp.equals("Z")) {
                    Label True = new Label();
                    Label End = new Label();
                    left.emit(cw, mv);
                    right.emit(cw, mv);
                    mv.visitInsn(I2D);
                    mv.visitInsn(DCMPG);
                    mv.visitInsn(ICONST_0);
                    mv.visitJumpInsn(IF_ICMPLE, True);
                    mv.visitInsn(DCONST_0);
                    mv.visitJumpInsn(GOTO, End);
                    mv.visitLabel(True);
                    mv.visitInsn(DCONST_1);
                    mv.visitLabel(End);
                }
//
                else if (left_exp.equals("Z") && right_exp.equals("I")) {
                    Label False = new Label();
                    Label End = new Label();
                    left.emit(cw, mv);
                    right.emit(cw, mv);
                    mv.visitInsn(ISUB);
                    mv.visitInsn(ICONST_0);
                    mv.visitJumpInsn(IF_ICMPGT, False);
                    mv.visitInsn(ICONST_1);
                    mv.visitJumpInsn(GOTO, End);
                    mv.visitLabel(False);
                    mv.visitInsn(ICONST_0);
                    mv.visitLabel(End);
                }
                else if (left_exp.equals("Z") && right_exp.equals("D")) {
                    Label True = new Label();
                    Label End = new Label();
                    left.emit(cw, mv);
                    mv.visitInsn(I2D);
                    right.emit(cw, mv);
                    mv.visitInsn(DCMPG);
                    mv.visitInsn(ICONST_0);
                    mv.visitJumpInsn(IF_ICMPLE, True);
                    mv.visitInsn(DCONST_0);
                    mv.visitJumpInsn(GOTO, End);
                    mv.visitLabel(True);
                    mv.visitInsn(DCONST_1);
                    mv.visitLabel(End);
                }
            }

            case NEQUALS -> {
                if (left_exp.equals("I") && right_exp.equals("I")) {
                    Label False = new Label();
                    Label End = new Label();
                    left.emit(cw, mv);
                    right.emit(cw, mv);
                    mv.visitInsn(ISUB);
                    mv.visitInsn(ICONST_0);
                    mv.visitJumpInsn(IF_ICMPEQ, False);
                    mv.visitInsn(ICONST_1);
                    mv.visitJumpInsn(GOTO, End);
                    mv.visitLabel(False);
                    mv.visitInsn(ICONST_0);
                    mv.visitLabel(End);
                }
                else if (left_exp.equals("I") && right_exp.equals("D")) {
                    Label True = new Label();
                    Label End = new Label();
                    left.emit(cw, mv);
                    mv.visitInsn(I2D);
                    right.emit(cw, mv);
                    mv.visitInsn(DCMPG);
                    mv.visitInsn(ICONST_0);
                    mv.visitJumpInsn(IF_ICMPEQ, True);
                    mv.visitInsn(DCONST_1);
                    mv.visitJumpInsn(GOTO, End);
                    mv.visitLabel(True);
                    mv.visitInsn(DCONST_0);
                    mv.visitLabel(End);
                }
                else if (left_exp.equals("I") && right_exp.equals("Z")) {
                    Label False = new Label();
                    Label End = new Label();
                    left.emit(cw, mv);
                    right.emit(cw, mv);
                    mv.visitInsn(ISUB);
                    mv.visitInsn(ICONST_0);
                    mv.visitJumpInsn(IF_ICMPEQ, False);
                    mv.visitInsn(ICONST_1);
                    mv.visitJumpInsn(GOTO, End);
                    mv.visitLabel(False);
                    mv.visitInsn(ICONST_0);
                    mv.visitLabel(End);
                }
                else if (left_exp.equals("D") && right_exp.equals("D")) {
                    Label True = new Label();
                    Label End = new Label();
                    left.emit(cw, mv);
                    right.emit(cw, mv);
                    mv.visitInsn(DCMPG);
                    mv.visitInsn(ICONST_0);
                    mv.visitJumpInsn(IF_ICMPEQ, True);
                    mv.visitInsn(DCONST_1);
                    mv.visitJumpInsn(GOTO, End);
                    mv.visitLabel(True);
                    mv.visitInsn(DCONST_0);
                    mv.visitLabel(End);
                }
                else if (left_exp.equals("Z") && right_exp.equals("Z")) {
                    Label False = new Label();
                    Label End = new Label();
                    left.emit(cw, mv);
                    right.emit(cw, mv);
                    mv.visitInsn(ISUB);
                    mv.visitInsn(ICONST_0);
                    mv.visitJumpInsn(IF_ICMPEQ, False);
                    mv.visitInsn(ICONST_1);
                    mv.visitJumpInsn(GOTO, End);
                    mv.visitLabel(False);
                    mv.visitInsn(ICONST_0);
                    mv.visitLabel(End);
                }
                else if (left_exp.equals("D") && right_exp.equals("I")) {
                    Label True = new Label();
                    Label End = new Label();
                    left.emit(cw, mv);
                    right.emit(cw, mv);
                    mv.visitInsn(I2D);
                    mv.visitInsn(DCMPG);
                    mv.visitInsn(ICONST_0);
                    mv.visitJumpInsn(IF_ICMPEQ, True);
                    mv.visitInsn(DCONST_1);
                    mv.visitJumpInsn(GOTO, End);
                    mv.visitLabel(True);
                    mv.visitInsn(DCONST_0);
                    mv.visitLabel(End);
                }
                else if (left_exp.equals("D") && right_exp.equals("Z")) {
                    Label True = new Label();
                    Label End = new Label();
                    left.emit(cw, mv);
                    right.emit(cw, mv);
                    mv.visitInsn(I2D);
                    mv.visitInsn(DCMPG);
                    mv.visitInsn(ICONST_0);
                    mv.visitJumpInsn(IF_ICMPEQ, True);
                    mv.visitInsn(DCONST_1);
                    mv.visitJumpInsn(GOTO, End);
                    mv.visitLabel(True);
                    mv.visitInsn(DCONST_0);
                    mv.visitLabel(End);
                }
                else if (left_exp.equals("Z") && right_exp.equals("I")) {
                    Label False = new Label();
                    Label End = new Label();
                    left.emit(cw, mv);
                    right.emit(cw, mv);
                    mv.visitInsn(ISUB);
                    mv.visitInsn(ICONST_0);
                    mv.visitJumpInsn(IF_ICMPEQ, False);
                    mv.visitInsn(ICONST_1);
                    mv.visitJumpInsn(GOTO, End);
                    mv.visitLabel(False);
                    mv.visitInsn(ICONST_0);
                    mv.visitLabel(End);
                }
                else if (left_exp.equals("Z") && right_exp.equals("D")) {
                    Label True = new Label();
                    Label End = new Label();
                    left.emit(cw, mv);
                    mv.visitInsn(I2D);
                    right.emit(cw, mv);
                    mv.visitInsn(DCMPG);
                    mv.visitInsn(ICONST_0);
                    mv.visitJumpInsn(IF_ICMPEQ, True);
                    mv.visitInsn(DCONST_1);
                    mv.visitJumpInsn(GOTO, End);
                    mv.visitLabel(True);
                    mv.visitInsn(DCONST_0);
                    mv.visitLabel(End);
                }
            }

            case EQUALS -> {
                if (left_exp.equals("I") && right_exp.equals("I")) {
                    Label False = new Label();
                    Label End = new Label();
                    left.emit(cw, mv);
                    right.emit(cw, mv);
                    mv.visitInsn(ISUB);
                    mv.visitInsn(ICONST_0);
                    mv.visitJumpInsn(IF_ICMPNE, False);
                    mv.visitInsn(ICONST_1);
                    mv.visitJumpInsn(GOTO, End);
                    mv.visitLabel(False);
                    mv.visitInsn(ICONST_0);
                    mv.visitLabel(End);
                }
                else if (left_exp.equals("I") && right_exp.equals("D")) {
                    Label True = new Label();
                    Label End = new Label();
                    left.emit(cw, mv);
                    mv.visitInsn(I2D);
                    right.emit(cw, mv);
                    mv.visitInsn(DCMPG);
                    mv.visitInsn(ICONST_0);
                    mv.visitJumpInsn(IF_ICMPEQ, True);
                    mv.visitInsn(DCONST_0);
                    mv.visitJumpInsn(GOTO, End);
                    mv.visitLabel(True);
                    mv.visitInsn(DCONST_1);
                    mv.visitLabel(End);
                }
                else if (left_exp.equals("I") && right_exp.equals("Z")) {
                    Label False = new Label();
                    Label End = new Label();
                    left.emit(cw, mv);
                    right.emit(cw, mv);
                    mv.visitInsn(ISUB);
                    mv.visitInsn(ICONST_0);
                    mv.visitJumpInsn(IF_ICMPNE, False);
                    mv.visitInsn(ICONST_1);
                    mv.visitJumpInsn(GOTO, End);
                    mv.visitLabel(False);
                    mv.visitInsn(ICONST_0);
                    mv.visitLabel(End);
                }
                else if (left_exp.equals("D") && right_exp.equals("D")) {
                    Label True = new Label();
                    Label End = new Label();
                    left.emit(cw, mv);
                    right.emit(cw, mv);
                    mv.visitInsn(DCMPG);
                    mv.visitInsn(ICONST_0);
                    mv.visitJumpInsn(IF_ICMPEQ, True);
                    mv.visitInsn(DCONST_0);
                    mv.visitJumpInsn(GOTO, End);
                    mv.visitLabel(True);
                    mv.visitInsn(DCONST_1);
                    mv.visitLabel(End);
                }
                else if (left_exp.equals("Z") && right_exp.equals("Z")) {
                    Label False = new Label();
                    Label End = new Label();
                    left.emit(cw, mv);
                    right.emit(cw, mv);
                    mv.visitInsn(ISUB);
                    mv.visitInsn(ICONST_0);
                    mv.visitJumpInsn(IF_ICMPNE, False);
                    mv.visitInsn(ICONST_1);
                    mv.visitJumpInsn(GOTO, End);
                    mv.visitLabel(False);
                    mv.visitInsn(ICONST_0);
                    mv.visitLabel(End);
                }
                else if (left_exp.equals("D") && right_exp.equals("I")) {
                    Label True = new Label();
                    Label End = new Label();
                    left.emit(cw, mv);
                    right.emit(cw, mv);
                    mv.visitInsn(I2D);
                    mv.visitInsn(DCMPG);
                    mv.visitInsn(ICONST_0);
                    mv.visitJumpInsn(IF_ICMPEQ, True);
                    mv.visitInsn(DCONST_0);
                    mv.visitJumpInsn(GOTO, End);
                    mv.visitLabel(True);
                    mv.visitInsn(DCONST_1);
                    mv.visitLabel(End);
                }
                else if (left_exp.equals("D") && right_exp.equals("Z")) {
                    Label True = new Label();
                    Label End = new Label();
                    left.emit(cw, mv);
                    right.emit(cw, mv);
                    mv.visitInsn(I2D);
                    mv.visitInsn(DCMPG);
                    mv.visitInsn(ICONST_0);
                    mv.visitJumpInsn(IF_ICMPEQ, True);
                    mv.visitInsn(DCONST_0);
                    mv.visitJumpInsn(GOTO, End);
                    mv.visitLabel(True);
                    mv.visitInsn(DCONST_1);
                    mv.visitLabel(End);
                }
                else if (left_exp.equals("Z") && right_exp.equals("I")) {
                    Label False = new Label();
                    Label End = new Label();
                    left.emit(cw, mv);
                    right.emit(cw, mv);
                    mv.visitInsn(ISUB);
                    mv.visitInsn(ICONST_0);
                    mv.visitJumpInsn(IF_ICMPNE, False);
                    mv.visitInsn(ICONST_1);
                    mv.visitJumpInsn(GOTO, End);
                    mv.visitLabel(False);
                    mv.visitInsn(ICONST_0);
                    mv.visitLabel(End);
                }
                else if (left_exp.equals("Z") && right_exp.equals("D")) {
                    Label True = new Label();
                    Label End = new Label();
                    left.emit(cw, mv);
                    mv.visitInsn(I2D);
                    right.emit(cw, mv);
                    mv.visitInsn(DCMPG);
                    mv.visitInsn(ICONST_0);
                    mv.visitJumpInsn(IF_ICMPEQ, True);
                    mv.visitInsn(DCONST_0);
                    mv.visitJumpInsn(GOTO, End);
                    mv.visitLabel(True);
                    mv.visitInsn(DCONST_1);
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
    public String resolve_type() {

        String left_exp = left.resolve_type();
        String right_exp = right.resolve_type();

        if (left_exp.equals("I") && right_exp.equals("I")) {
            return "I";
        }
        else if (left_exp.equals("I") && right_exp.equals("D")) {
            return "D";
        }
        else if (left_exp.equals("I") && right_exp.equals("Z")) {
            return "I";
        }
        else if (left_exp.equals("D") && right_exp.equals("D")) {
            return "D";
        }
        else if (left_exp.equals("Z") && right_exp.equals("Z")) {
            return "Z";
        }
        else if (left_exp.equals("D") && right_exp.equals("I")) {
            return "D";
        }
        else if (left_exp.equals("D") && right_exp.equals("Z")) {
            return "D";
        }
        else if (left_exp.equals("Z") && right_exp.equals("I")) {
            return "I";
        }
        else if (left_exp.equals("Z") && right_exp.equals("D")) {
            return "D";
        }
        else {
            System.out.println("Something wrong with types");
            return null;
        }

    }
}
