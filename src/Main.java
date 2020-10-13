import lexer.MyLexer;
import lexer.Token;
import lexer.TokenType;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;
import parser.YYParser;
import reader.Reader;
import simple.Declarations;

import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collections;

import static org.objectweb.asm.Opcodes.V1_8;
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

        Declarations ast = YYParser.makeAST("tests/25.txt");



        System.out.println("Is Ast built?    "+ Boolean.toString(ast != null) + '\n');
        assert ast != null;
        Collections.reverse(ast);
        for (var decl : ast) {
            try {
                System.out.println(decl);
                decl.emit(cw, mv, "main", 0);
            }
            catch (RuntimeException e) {
                System.out.println(e.getStackTrace());
            }
        }
        mv.visitInsn(ICONST_0);
        mv.visitMethodInsn(INVOKESTATIC, "MetaMain", "main_", "(I)V", false);
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




