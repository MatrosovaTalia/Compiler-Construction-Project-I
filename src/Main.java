import lexer.Lexer;
import lexer.Token;
import lexer.TokenType;
import reader.Reader;

import java.io.FileWriter;
import java.io.IOException;

public class Main {
    private static void testNToFile(int n) {
        Reader reader = new Reader();
        Lexer lexer = new Lexer();
        for (int i = 1; i <= n; i++) {
            reader.read("tests/" + i + ".txt");
            lexer.tokenize(reader.sourceText);
            try {
                FileWriter writer = new FileWriter("results/" + i + ".txt");
                TokenType type;
                do {
                    Token tok = lexer.lex();
                    type = tok.getType();
                    writer.write(tok.toString());
                } while (type != TokenType.EOF);
                writer.close();

                System.out.println("Successfully wrote to " + i + ".txt");
            } catch (IOException e) {
                System.out.println("An error occurred at " + i + ".txt");
                e.printStackTrace();
            }
        }
    }


    public static void main(String[] args) {
        testNToFile(15);
    }
}




