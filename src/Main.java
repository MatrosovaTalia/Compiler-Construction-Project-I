import lexer.MyLexer;
import lexer.Token;
import lexer.TokenType;
import reader.Reader;
import parser.*;
import simple.Declarations;
import simple.Identifier;
import simple.Operation;
import org.objectweb.asm.*;

import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;

import static org.objectweb.asm.Opcodes.*;

public class Main {
    private static void testNToFile(int n) {
        Reader reader = new Reader();
        MyLexer myLexer = new MyLexer();
        for (int i = 1; i <= n; i++) {
            reader.read("tests/" + i + ".txt");
            myLexer.tokenize(reader.sourceText);
            try {
                FileWriter writer = new FileWriter("results/" + i + ".txt");
                TokenType type;
                do {
                    Token tok = myLexer.lex();
                    type = tok.getType();
                    writer.write(tok.toString());
                } while (type != TokenType.YYEOF);
                writer.close();

                System.out.println("Successfully wrote to " + i + ".txt");
            } catch (IOException e) {
                System.out.println("An error occurred at " + i + ".txt");
                e.printStackTrace();
            }
        }
    }


    public static void main(String[] args) {

//         testNToFile(18);
//        Reader reader = new Reader();
//        MyLexer myLexer = new MyLexer();

        ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_FRAMES);
        cw.visit(V1_8, ACC_PUBLIC,
                "MetaMain", null, "java/lang/Object", null);
        MethodVisitor mv = cw.visitMethod(ACC_PUBLIC + ACC_STATIC, "main",
                "([Ljava/lang/String;)V", null, null);

        Declarations ast = YYParser.makeAST("25");



        System.out.println("Is Ast built?    "+ Boolean.toString(ast != null) + '\n');
        if (ast != null) {
            for (simple.IDeclaration iDeclaration : ast) {
                System.out.println(iDeclaration);
                iDeclaration.emit(cw, mv);
            }
        }
        mv.visitFieldInsn(GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
        mv.visitFieldInsn(GETSTATIC, "MetaMain", "a", "I");
        mv.visitMethodInsn(INVOKEVIRTUAL,
                "java/io/PrintStream",
                "println",
                "(I)V",
                false);
        mv.visitFieldInsn(GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
        mv.visitFieldInsn(GETSTATIC, "MetaMain", "b", "Z");
        mv.visitMethodInsn(INVOKEVIRTUAL,
                "java/io/PrintStream",
                "println",
                "(Z)V",
                false);
        mv.visitFieldInsn(GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
        mv.visitFieldInsn(GETSTATIC, "MetaMain", "z", "D");
        mv.visitMethodInsn(INVOKEVIRTUAL,
                "java/io/PrintStream",
                "println",
                "(D)V",
                false);

        mv.visitInsn(RETURN);
        mv.visitMaxs(-1, -1);
        mv.visitEnd();
        cw.visitEnd();

        byte[] b = cw.toByteArray();
        String class_name = "MetaMain.class";
        try (FileOutputStream stream = new FileOutputStream(class_name)) {
            stream.write(b);
        } catch (IOException e) {
            e.printStackTrace();
        }



    }
}




