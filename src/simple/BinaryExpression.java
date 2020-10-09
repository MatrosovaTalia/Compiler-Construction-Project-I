package simple;

import org.objectweb.asm.ClassWriter;
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
    public void emit(ClassWriter cw, MethodVisitor mv, String methodName) {


        switch (operation) {
            case PLUS -> {
                if (left.resolve_type().equals("I") && right.resolve_type().equals("I")) {
                    left.emit(cw, mv, methodName);
                    right.emit(cw, mv, methodName);
                    mv.visitInsn(IADD);
                }
                else if (left.resolve_type().equals("I") && right.resolve_type().equals("D")) {
                    left.emit(cw, mv, methodName);
                    mv.visitInsn(I2D);
                    right.emit(cw, mv, methodName);
                    mv.visitInsn(DADD);
                }
                else if (left.resolve_type().equals("I") && right.resolve_type().equals("Z")) {
                    left.emit(cw, mv, methodName);
                    right.emit(cw, mv, methodName);
                    mv.visitInsn(IADD);
                }
                else if (left.resolve_type().equals("D") && right.resolve_type().equals("D")) {
                    left.emit(cw, mv, methodName);
                    right.emit(cw, mv, methodName);
                    mv.visitInsn(DADD);
                }
                else if (left.resolve_type().equals("Z") && right.resolve_type().equals("Z")) {
                    left.emit(cw, mv, methodName);
                    right.emit(cw, mv, methodName);
                    mv.visitInsn(IADD);
                }
                else if (left.resolve_type().equals("D") && right.resolve_type().equals("I")) {
                    left.emit(cw, mv, methodName);
                    right.emit(cw, mv, methodName);
                    mv.visitInsn(I2D);
                    mv.visitInsn(DADD);
                }
                else if (left.resolve_type().equals("D") && right.resolve_type().equals("Z")) {
                    left.emit(cw, mv, methodName);
                    right.emit(cw, mv, methodName);
                    mv.visitInsn(I2D);
                    mv.visitInsn(DADD);
                }
                else if (left.resolve_type().equals("Z") && right.resolve_type().equals("I")) {
                    left.emit(cw, mv, methodName);
                    right.emit(cw, mv, methodName);
                    mv.visitInsn(IADD);
                }
                else if (left.resolve_type().equals("Z") && right.resolve_type().equals("D")) {
                    left.emit(cw, mv, methodName);
                    mv.visitInsn(I2D);
                    right.emit(cw, mv, methodName);
                    mv.visitInsn(DADD);
                }
            }

            case MINUS -> {
                if (left.resolve_type().equals("I") && right.resolve_type().equals("I")) {
                    left.emit(cw, mv);
                    right.emit(cw, mv);
                    mv.visitInsn(ISUB);
                }
                else if (left.resolve_type().equals("I") && right.resolve_type().equals("D")) {
                    left.emit(cw, mv);
                    mv.visitInsn(I2D);
                    right.emit(cw, mv);
                    mv.visitInsn(DSUB);
                }
                else if (left.resolve_type().equals("I") && right.resolve_type().equals("Z")) {
                    left.emit(cw, mv);
                    right.emit(cw, mv);
                    mv.visitInsn(ISUB);
                }
                else if (left.resolve_type().equals("D") && right.resolve_type().equals("D")) {
                    left.emit(cw, mv);
                    right.emit(cw, mv);
                    mv.visitInsn(DSUB);
                }
                else if (left.resolve_type().equals("Z") && right.resolve_type().equals("Z")) {
                    left.emit(cw, mv);
                    right.emit(cw, mv);
                    mv.visitInsn(ISUB);
                }
                else if (left.resolve_type().equals("D") && right.resolve_type().equals("I")) {
                    left.emit(cw, mv);
                    right.emit(cw, mv);
                    mv.visitInsn(I2D);
                    mv.visitInsn(DSUB);
                }
                else if (left.resolve_type().equals("D") && right.resolve_type().equals("Z")) {
                    left.emit(cw, mv);
                    right.emit(cw, mv);
                    mv.visitInsn(I2D);
                    mv.visitInsn(DSUB);
                }
                else if (left.resolve_type().equals("Z") && right.resolve_type().equals("I")) {
                    left.emit(cw, mv);
                    right.emit(cw, mv);
                    mv.visitInsn(ISUB);
                }
                else if (left.resolve_type().equals("Z") && right.resolve_type().equals("D")) {
                    left.emit(cw, mv);
                    mv.visitInsn(I2D);
                    right.emit(cw, mv);
                    mv.visitInsn(DSUB);
                }
            }


            case MULTIPLY -> {
                if (left.resolve_type().equals("I") && right.resolve_type().equals("I")) {
                    left.emit(cw, mv);
                    right.emit(cw, mv);
                    mv.visitInsn(IMUL);
                }
                else if (left.resolve_type().equals("I") && right.resolve_type().equals("D")) {
                    left.emit(cw, mv);
                    mv.visitInsn(I2D);
                    right.emit(cw, mv);
                    mv.visitInsn(DMUL);
                }
                else if (left.resolve_type().equals("I") && right.resolve_type().equals("Z")) {
                    left.emit(cw, mv);
                    right.emit(cw, mv);
                    mv.visitInsn(IMUL);
                }
                else if (left.resolve_type().equals("D") && right.resolve_type().equals("D")) {
                    left.emit(cw, mv);
                    right.emit(cw, mv);
                    mv.visitInsn(DMUL);
                }
                else if (left.resolve_type().equals("Z") && right.resolve_type().equals("Z")) {
                    left.emit(cw, mv);
                    right.emit(cw, mv);
                    mv.visitInsn(IMUL);
                }
                else if (left.resolve_type().equals("D") && right.resolve_type().equals("I")) {
                    left.emit(cw, mv);
                    right.emit(cw, mv);
                    mv.visitInsn(I2D);
                    mv.visitInsn(DMUL);
                }
                else if (left.resolve_type().equals("D") && right.resolve_type().equals("Z")) {
                    left.emit(cw, mv);
                    right.emit(cw, mv);
                    mv.visitInsn(I2D);
                    mv.visitInsn(DMUL);
                }
                else if (left.resolve_type().equals("Z") && right.resolve_type().equals("I")) {
                    left.emit(cw, mv);
                    right.emit(cw, mv);
                    mv.visitInsn(IMUL);
                }
                else if (left.resolve_type().equals("Z") && right.resolve_type().equals("D")) {
                    left.emit(cw, mv);
                    mv.visitInsn(I2D);
                    right.emit(cw, mv);
                    mv.visitInsn(DMUL);
                }
            }

            case DIVIDE -> {
                if (left.resolve_type().equals("I") && right.resolve_type().equals("I")) {
                    left.emit(cw, mv);
                    right.emit(cw, mv);
                    mv.visitInsn(IDIV);
                }
                else if (left.resolve_type().equals("I") && right.resolve_type().equals("D")) {
                    left.emit(cw, mv);
                    mv.visitInsn(I2D);
                    right.emit(cw, mv);
                    mv.visitInsn(DDIV);
                }
                else if (left.resolve_type().equals("I") && right.resolve_type().equals("Z")) {
                    left.emit(cw, mv);
                    right.emit(cw, mv);
                    mv.visitInsn(IDIV);
                }
                else if (left.resolve_type().equals("D") && right.resolve_type().equals("D")) {
                    left.emit(cw, mv);
                    right.emit(cw, mv);
                    mv.visitInsn(DDIV);
                }
                else if (left.resolve_type().equals("Z") && right.resolve_type().equals("Z")) {
                    left.emit(cw, mv);
                    right.emit(cw, mv);
                    mv.visitInsn(IDIV);
                }
                else if (left.resolve_type().equals("D") && right.resolve_type().equals("I")) {
                    left.emit(cw, mv);
                    right.emit(cw, mv);
                    mv.visitInsn(I2D);
                    mv.visitInsn(DDIV);
                }
                else if (left.resolve_type().equals("D") && right.resolve_type().equals("Z")) {
                    left.emit(cw, mv);
                    right.emit(cw, mv);
                    mv.visitInsn(I2D);
                    mv.visitInsn(DDIV);
                }
                else if (left.resolve_type().equals("Z") && right.resolve_type().equals("I")) {
                    left.emit(cw, mv);
                    right.emit(cw, mv);
                    mv.visitInsn(IDIV);
                }
                else if (left.resolve_type().equals("Z") && right.resolve_type().equals("D")) {
                    left.emit(cw, mv);
                    mv.visitInsn(I2D);
                    right.emit(cw, mv);
                    mv.visitInsn(DDIV);
                }
            }

            case REMAINDER -> {
                if (left.resolve_type().equals("I") && right.resolve_type().equals("I")) {
                    left.emit(cw, mv);
                    right.emit(cw, mv);
                    mv.visitInsn(IREM);
                }
                else if (left.resolve_type().equals("I") && right.resolve_type().equals("D")) {
                    left.emit(cw, mv);
                    mv.visitInsn(I2D);
                    right.emit(cw, mv);
                    mv.visitInsn(DREM);
                }
                else if (left.resolve_type().equals("I") && right.resolve_type().equals("Z")) {
                    left.emit(cw, mv);
                    right.emit(cw, mv);
                    mv.visitInsn(IREM);
                }
                else if (left.resolve_type().equals("D") && right.resolve_type().equals("D")) {
                    left.emit(cw, mv);
                    right.emit(cw, mv);
                    mv.visitInsn(DREM);
                }
                else if (left.resolve_type().equals("Z") && right.resolve_type().equals("Z")) {
                    left.emit(cw, mv);
                    right.emit(cw, mv);
                    mv.visitInsn(IREM);
                }
                else if (left.resolve_type().equals("D") && right.resolve_type().equals("I")) {
                    left.emit(cw, mv);
                    right.emit(cw, mv);
                    mv.visitInsn(I2D);
                    mv.visitInsn(DREM);
                }
                else if (left.resolve_type().equals("D") && right.resolve_type().equals("Z")) {
                    left.emit(cw, mv);
                    right.emit(cw, mv);
                    mv.visitInsn(I2D);
                    mv.visitInsn(DREM);
                }
                else if (left.resolve_type().equals("Z") && right.resolve_type().equals("I")) {
                    left.emit(cw, mv);
                    right.emit(cw, mv);
                    mv.visitInsn(IREM);
                }
                else if (left.resolve_type().equals("Z") && right.resolve_type().equals("D")) {
                    left.emit(cw, mv);
                    mv.visitInsn(I2D);
                    right.emit(cw, mv);
                    mv.visitInsn(DREM);
                }
            }

            case AND -> {
                if (left.resolve_type().equals("I") && right.resolve_type().equals("I")) {
                    left.emit(cw, mv);
                    right.emit(cw, mv);
                    mv.visitInsn(IAND);
                }
                else if (left.resolve_type().equals("I") && right.resolve_type().equals("D")) {
                    throw new RuntimeException("Illegal expression: Bitwise operation does not accept real value");
                }
                else if (left.resolve_type().equals("I") && right.resolve_type().equals("Z")) {
                    left.emit(cw, mv);
                    right.emit(cw, mv);
                    mv.visitInsn(IAND);
                }
                else if (left.resolve_type().equals("D") && right.resolve_type().equals("D")) {
                    throw new RuntimeException("Illegal expression: Bitwise operation does not accept real value");
                }
                else if (left.resolve_type().equals("Z") && right.resolve_type().equals("Z")) {
                    left.emit(cw, mv);
                    right.emit(cw, mv);
                    mv.visitInsn(IAND);
                }
                else if (left.resolve_type().equals("D") && right.resolve_type().equals("I")) {
                    throw new RuntimeException("Illegal expression: Bitwise operation does not accept real value");
                }
                else if (left.resolve_type().equals("D") && right.resolve_type().equals("Z")) {
                    throw new RuntimeException("Illegal expression: Bitwise operation does not accept real value");
                }
                else if (left.resolve_type().equals("Z") && right.resolve_type().equals("I")) {
                    left.emit(cw, mv);
                    right.emit(cw, mv);
                    mv.visitInsn(IAND);
                }
                else if (left.resolve_type().equals("Z") && right.resolve_type().equals("D")) {
                    throw new RuntimeException("Illegal expression: Bitwise operation does not accept real value");
                }
            }

            case OR -> {
                if (left.resolve_type().equals("I") && right.resolve_type().equals("I")) {
                    left.emit(cw, mv);
                    right.emit(cw, mv);
                    mv.visitInsn(IOR);
                }
                else if (left.resolve_type().equals("I") && right.resolve_type().equals("D")) {
                    throw new RuntimeException("Illegal expression: Bitwise operation does not accept real value");
                }
                else if (left.resolve_type().equals("I") && right.resolve_type().equals("Z")) {
                    left.emit(cw, mv);
                    right.emit(cw, mv);
                    mv.visitInsn(IOR);
                }
                else if (left.resolve_type().equals("D") && right.resolve_type().equals("D")) {
                    throw new RuntimeException("Illegal expression: Bitwise operation does not accept real value");
                }
                else if (left.resolve_type().equals("Z") && right.resolve_type().equals("Z")) {
                    left.emit(cw, mv);
                    right.emit(cw, mv);
                    mv.visitInsn(IOR);
                }
                else if (left.resolve_type().equals("D") && right.resolve_type().equals("I")) {
                    throw new RuntimeException("Illegal expression: Bitwise operation does not accept real value");
                }
                else if (left.resolve_type().equals("D") && right.resolve_type().equals("Z")) {
                    throw new RuntimeException("Illegal expression: Bitwise operation does not accept real value");
                }
                else if (left.resolve_type().equals("Z") && right.resolve_type().equals("I")) {
                    left.emit(cw, mv);
                    right.emit(cw, mv);
                    mv.visitInsn(IOR);
                }
                else if (left.resolve_type().equals("Z") && right.resolve_type().equals("D")) {
                    throw new RuntimeException("Illegal expression: Bitwise operation does not accept real value");
                }
            }

            case XOR -> {
                if (left.resolve_type().equals("I") && right.resolve_type().equals("I")) {
                    left.emit(cw, mv);
                    right.emit(cw, mv);
                    mv.visitInsn(IXOR);
                }
                else if (left.resolve_type().equals("I") && right.resolve_type().equals("D")) {
                    throw new RuntimeException("Illegal expression: Bitwise operation does not accept real value");
                }
                else if (left.resolve_type().equals("I") && right.resolve_type().equals("Z")) {
                    left.emit(cw, mv);
                    right.emit(cw, mv);
                    mv.visitInsn(IXOR);
                }
                else if (left.resolve_type().equals("D") && right.resolve_type().equals("D")) {
                    throw new RuntimeException("Illegal expression: Bitwise operation does not accept real value");
                }
                else if (left.resolve_type().equals("Z") && right.resolve_type().equals("Z")) {
                    left.emit(cw, mv);
                    right.emit(cw, mv);
                    mv.visitInsn(IXOR);
                }
                else if (left.resolve_type().equals("D") && right.resolve_type().equals("I")) {
                    throw new RuntimeException("Illegal expression: Bitwise operation does not accept real value");
                }
                else if (left.resolve_type().equals("D") && right.resolve_type().equals("Z")) {
                    throw new RuntimeException("Illegal expression: Bitwise operation does not accept real value");
                }
                else if (left.resolve_type().equals("Z") && right.resolve_type().equals("I")) {
                    left.emit(cw, mv);
                    right.emit(cw, mv);
                    mv.visitInsn(IXOR);
                }
                else if (left.resolve_type().equals("Z") && right.resolve_type().equals("D")) {
                    throw new RuntimeException("Illegal expression: Bitwise operation does not accept real value");
                }
            }

            case GREATER -> {
                if (left.resolve_type().equals("I") && right.resolve_type().equals("I")) {
                    left.emit(cw, mv);
                    right.emit(cw, mv);
                    mv.visitInsn(IFGT);
                }
                else if (left.resolve_type().equals("I") && right.resolve_type().equals("D")) {
                    left.emit(cw, mv);
                    mv.visitInsn(I2D);
                    right.emit(cw, mv);
                    mv.visitInsn(DCMPG);
                }
                else if (left.resolve_type().equals("I") && right.resolve_type().equals("Z")) {
                    left.emit(cw, mv);
                    right.emit(cw, mv);
                    mv.visitInsn(IFGT);
                }
                else if (left.resolve_type().equals("D") && right.resolve_type().equals("D")) {
                    left.emit(cw, mv);
                    right.emit(cw, mv);
                    mv.visitInsn(DCMPG);
                }
                else if (left.resolve_type().equals("Z") && right.resolve_type().equals("Z")) {
                    left.emit(cw, mv);
                    right.emit(cw, mv);
                    mv.visitInsn(IFGT);
                }
                else if (left.resolve_type().equals("D") && right.resolve_type().equals("I")) {
                    left.emit(cw, mv);
                    right.emit(cw, mv);
                    mv.visitInsn(I2D);
                    mv.visitInsn(DCMPG);
                }
                else if (left.resolve_type().equals("D") && right.resolve_type().equals("Z")) {
                    left.emit(cw, mv);
                    right.emit(cw, mv);
                    mv.visitInsn(I2D);
                    mv.visitInsn(DCMPG);
                }
                else if (left.resolve_type().equals("Z") && right.resolve_type().equals("I")) {
                    left.emit(cw, mv);
                    right.emit(cw, mv);
                    mv.visitInsn(IFGT);
                }
                else if (left.resolve_type().equals("Z") && right.resolve_type().equals("D")) {
                    left.emit(cw, mv);
                    mv.visitInsn(I2D);
                    right.emit(cw, mv);
                    mv.visitInsn(DCMPG);
                }
            }

//            case GEQUALS -> {
//                if (left.resolve_type().equals("I") && right.resolve_type().equals("I")) {
//                    left.emit(cw, mv);
//                    right.emit(cw, mv);
//                    mv.visitInsn();
//                }
//                else if (left.resolve_type().equals("I") && right.resolve_type().equals("D")) {
//                    left.emit(cw, mv);
//                    mv.visitInsn(I2D);
//                    right.emit(cw, mv);
//                    mv.visitInsn();
//                }
//                else if (left.resolve_type().equals("I") && right.resolve_type().equals("Z")) {
//                    left.emit(cw, mv);
//                    right.emit(cw, mv);
//                    mv.visitInsn();
//                }
//                else if (left.resolve_type().equals("D") && right.resolve_type().equals("D")) {
//                    left.emit(cw, mv);
//                    right.emit(cw, mv);
//                    mv.visitInsn();
//                }
//                else if (left.resolve_type().equals("Z") && right.resolve_type().equals("Z")) {
//                    left.emit(cw, mv);
//                    right.emit(cw, mv);
//                    mv.visitInsn();
//                }
//                else if (left.resolve_type().equals("D") && right.resolve_type().equals("I")) {
//                    left.emit(cw, mv);
//                    right.emit(cw, mv);
//                    mv.visitInsn(I2D);
//                    mv.visitInsn();
//                }
//                else if (left.resolve_type().equals("D") && right.resolve_type().equals("Z")) {
//                    left.emit(cw, mv);
//                    right.emit(cw, mv);
//                    mv.visitInsn(I2D);
//                    mv.visitInsn();
//                }
//                else if (left.resolve_type().equals("Z") && right.resolve_type().equals("I")) {
//                    left.emit(cw, mv);
//                    right.emit(cw, mv);
//                    mv.visitInsn();
//                }
//                else if (left.resolve_type().equals("Z") && right.resolve_type().equals("D")) {
//                    left.emit(cw, mv);
//                    mv.visitInsn(I2D);
//                    right.emit(cw, mv);
//                    mv.visitInsn();
//                }
//            }

            case LESS -> {
                if (left.resolve_type().equals("I") && right.resolve_type().equals("I")) {
                    left.emit(cw, mv);
                    right.emit(cw, mv);
                    mv.visitInsn(IFLT);
                }
                else if (left.resolve_type().equals("I") && right.resolve_type().equals("D")) {
                    left.emit(cw, mv);
                    mv.visitInsn(I2D);
                    right.emit(cw, mv);
                    mv.visitInsn(DCMPL);
                }
                else if (left.resolve_type().equals("I") && right.resolve_type().equals("Z")) {
                    left.emit(cw, mv);
                    right.emit(cw, mv);
                    mv.visitInsn(IFLT);
                }
                else if (left.resolve_type().equals("D") && right.resolve_type().equals("D")) {
                    left.emit(cw, mv);
                    right.emit(cw, mv);
                    mv.visitInsn(DCMPL);
                }
                else if (left.resolve_type().equals("Z") && right.resolve_type().equals("Z")) {
                    left.emit(cw, mv);
                    right.emit(cw, mv);
                    mv.visitInsn(IFLT);
                }
                else if (left.resolve_type().equals("D") && right.resolve_type().equals("I")) {
                    left.emit(cw, mv);
                    right.emit(cw, mv);
                    mv.visitInsn(I2D);
                    mv.visitInsn(DCMPL);
                }
                else if (left.resolve_type().equals("D") && right.resolve_type().equals("Z")) {
                    left.emit(cw, mv);
                    right.emit(cw, mv);
                    mv.visitInsn(I2D);
                    mv.visitInsn(DCMPL);
                }
                else if (left.resolve_type().equals("Z") && right.resolve_type().equals("I")) {
                    left.emit(cw, mv);
                    right.emit(cw, mv);
                    mv.visitInsn(IFLT);
                }
                else if (left.resolve_type().equals("Z") && right.resolve_type().equals("D")) {
                    left.emit(cw, mv);
                    mv.visitInsn(I2D);
                    right.emit(cw, mv);
                    mv.visitInsn(DCMPL);
                }
            }

//            case LEQUALS -> {
//                if (left.resolve_type().equals("I") && right.resolve_type().equals("I")) {
//                    left.emit(cw, mv);
//                    right.emit(cw, mv);
//                    mv.visitInsn(IFLE);
//                }
//                else if (left.resolve_type().equals("I") && right.resolve_type().equals("D")) {
//                    left.emit(cw, mv);
//                    mv.visitInsn(I2D);
//                    right.emit(cw, mv);
//                    mv.visitInsn();
//                }
//                else if (left.resolve_type().equals("I") && right.resolve_type().equals("Z")) {
//                    left.emit(cw, mv);
//                    right.emit(cw, mv);
//                    mv.visitInsn(IFLE);
//                }
//                else if (left.resolve_type().equals("D") && right.resolve_type().equals("D")) {
//                    left.emit(cw, mv);
//                    right.emit(cw, mv);
//                    mv.visitInsn();
//                }
//                else if (left.resolve_type().equals("Z") && right.resolve_type().equals("Z")) {
//                    left.emit(cw, mv);
//                    right.emit(cw, mv);
//                    mv.visitInsn(IFLE);
//                }
//                else if (left.resolve_type().equals("D") && right.resolve_type().equals("I")) {
//                    left.emit(cw, mv);
//                    right.emit(cw, mv);
//                    mv.visitInsn(I2D);
//                    mv.visitInsn();
//                }
//                else if (left.resolve_type().equals("D") && right.resolve_type().equals("Z")) {
//                    left.emit(cw, mv);
//                    right.emit(cw, mv);
//                    mv.visitInsn(I2D);
//                    mv.visitInsn();
//                }
//                else if (left.resolve_type().equals("Z") && right.resolve_type().equals("I")) {
//                    left.emit(cw, mv);
//                    right.emit(cw, mv);
//                    mv.visitInsn(IFLE);
//                }
//                else if (left.resolve_type().equals("Z") && right.resolve_type().equals("D")) {
//                    left.emit(cw, mv);
//                    mv.visitInsn(I2D);
//                    right.emit(cw, mv);
//                    mv.visitInsn();
//                }
//            }

//            case NEQUALS -> {
//                if (left.resolve_type().equals("I") && right.resolve_type().equals("I")) {
//                    left.emit(cw, mv);
//                    right.emit(cw, mv);
//                    mv.visitInsn();
//                }
//                else if (left.resolve_type().equals("I") && right.resolve_type().equals("D")) {
//                    left.emit(cw, mv);
//                    mv.visitInsn(I2D);
//                    right.emit(cw, mv);
//                    mv.visitInsn();
//                }
//                else if (left.resolve_type().equals("I") && right.resolve_type().equals("Z")) {
//                    left.emit(cw, mv);
//                    right.emit(cw, mv);
//                    mv.visitInsn();
//                }
//                else if (left.resolve_type().equals("D") && right.resolve_type().equals("D")) {
//                    left.emit(cw, mv);
//                    right.emit(cw, mv);
//                    mv.visitInsn();
//                }
//                else if (left.resolve_type().equals("Z") && right.resolve_type().equals("Z")) {
//                    left.emit(cw, mv);
//                    right.emit(cw, mv);
//                    mv.visitInsn();
//                }
//                else if (left.resolve_type().equals("D") && right.resolve_type().equals("I")) {
//                    left.emit(cw, mv);
//                    right.emit(cw, mv);
//                    mv.visitInsn(I2D);
//                    mv.visitInsn();
//                }
//                else if (left.resolve_type().equals("D") && right.resolve_type().equals("Z")) {
//                    left.emit(cw, mv);
//                    right.emit(cw, mv);
//                    mv.visitInsn(I2D);
//                    mv.visitInsn();
//                }
//                else if (left.resolve_type().equals("Z") && right.resolve_type().equals("I")) {
//                    left.emit(cw, mv);
//                    right.emit(cw, mv);
//                    mv.visitInsn();
//                }
//                else if (left.resolve_type().equals("Z") && right.resolve_type().equals("D")) {
//                    left.emit(cw, mv);
//                    mv.visitInsn(I2D);
//                    right.emit(cw, mv);
//                    mv.visitInsn();
//                }
//            }

//            case EQUALS -> {
//                if (left.resolve_type().equals("I") && right.resolve_type().equals("I")) {
//                    left.emit(cw, mv);
//                    right.emit(cw, mv);
//                    mv.visitInsn();
//                }
//                else if (left.resolve_type().equals("I") && right.resolve_type().equals("D")) {
//                    left.emit(cw, mv);
//                    mv.visitInsn(I2D);
//                    right.emit(cw, mv);
//                    mv.visitInsn();
//                }
//                else if (left.resolve_type().equals("I") && right.resolve_type().equals("Z")) {
//                    left.emit(cw, mv);
//                    right.emit(cw, mv);
//                    mv.visitInsn();
//                }
//                else if (left.resolve_type().equals("D") && right.resolve_type().equals("D")) {
//                    left.emit(cw, mv);
//                    right.emit(cw, mv);
//                    mv.visitInsn();
//                }
//                else if (left.resolve_type().equals("Z") && right.resolve_type().equals("Z")) {
//                    left.emit(cw, mv);
//                    right.emit(cw, mv);
//                    mv.visitInsn();
//                }
//                else if (left.resolve_type().equals("D") && right.resolve_type().equals("I")) {
//                    left.emit(cw, mv);
//                    right.emit(cw, mv);
//                    mv.visitInsn(I2D);
//                    mv.visitInsn();
//                }
//                else if (left.resolve_type().equals("D") && right.resolve_type().equals("Z")) {
//                    left.emit(cw, mv);
//                    right.emit(cw, mv);
//                    mv.visitInsn(I2D);
//                    mv.visitInsn();
//                }
//                else if (left.resolve_type().equals("Z") && right.resolve_type().equals("I")) {
//                    left.emit(cw, mv);
//                    right.emit(cw, mv);
//                    mv.visitInsn();
//                }
//                else if (left.resolve_type().equals("Z") && right.resolve_type().equals("D")) {
//                    left.emit(cw, mv);
//                    mv.visitInsn(I2D);
//                    right.emit(cw, mv);
//                    mv.visitInsn();
//                }
//            }


        }
    }

    @Override
    public Object resolve_value() {
        return null;
    }

    @Override
    public String resolve_type() {
        if (left.resolve_type().equals("I") && right.resolve_type().equals("I")) {
            return "I";
        }
        else if (left.resolve_type().equals("I") && right.resolve_type().equals("D")) {
            return "D";
        }
        else if (left.resolve_type().equals("I") && right.resolve_type().equals("Z")) {
            return "I";
        }
        else if (left.resolve_type().equals("D") && right.resolve_type().equals("D")) {
            return "D";
        }
        else if (left.resolve_type().equals("Z") && right.resolve_type().equals("Z")) {
            return "Z";
        }
        else if (left.resolve_type().equals("D") && right.resolve_type().equals("I")) {
            return "D";
        }
        else if (left.resolve_type().equals("D") && right.resolve_type().equals("Z")) {
            return "D";
        }
        else if (left.resolve_type().equals("Z") && right.resolve_type().equals("I")) {
            return "I";
        }
        else if (left.resolve_type().equals("Z") && right.resolve_type().equals("D")) {
            return "D";
        }
        else {
            System.out.println("Something wrong with types");
            return null;
        }

    }
}
