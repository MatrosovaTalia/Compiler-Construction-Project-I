import lexer.MyLexer;
import lexer.Token;
import lexer.TokenType;
import reader.Reader;
import lexems.*;
import parser.*;
import simple.Declarations;

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

        Declarations ast = YYParser.makeAST("17");

        Identifier id = new Identifier("id1");
        System.out.println(id);
        System.out.println("Is Ast built?    "+ Boolean.toString(ast != null));
        for (int i =0; i < ast.size(); i++){
            System.out.println(ast.get(i));

        }

    }
}




