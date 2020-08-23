import java.io.IOException;

public class Test {
    public static void main(String[] args) {
        Lexer lexer = new Lexer();
        try {
            int i = 0;
            for (Token t : lexer.lex("tests/15.txt")) {
                System.out.print(t + " ");
                i += 1;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}




