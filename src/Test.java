import java.io.IOException;

public class Test {
    public static void main(String[] args) {
        Lexer lexer = new Lexer();
        try {
            for (Token t: lexer.lex("tests/12.txt")) {
                System.out.print(t.getBody() + " ");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}




