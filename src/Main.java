import lexer.MyLexer;
import lexer.Token;
import lexer.TokenType;
import reader.Reader;
import lexems.*;
import parser.*;

import java.io.FileWriter;
import java.io.IOException;

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

//         testNToFile(15);

        GlobalDeclarations ast = YYParser.makeAST("3");

        System.out.println(ast == null);

    }
}




