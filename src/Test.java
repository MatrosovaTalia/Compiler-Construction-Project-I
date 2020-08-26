import java.io.IOException;

public class Test {
    public static void main(String[] args) {
        Lexer lexer = new Lexer();
        try {
            for (Token t : lexer.lex("tests/14.txt")) {
                System.out.print(t + " ");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}




