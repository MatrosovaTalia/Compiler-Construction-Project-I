import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Test {
    private static void testN_toFile(int n){
        Reader reader = new Reader();
        Lexer lexer = new Lexer();
        for (int i = 1; i <= n; i++){
            reader.read("tests/" + i + ".txt");
            lexer.tokenize(reader.sourceText);
            try {
                FileWriter writer = new FileWriter("results/" + i + ".txt");
                for (Token t : lexer.lex()) {
                    writer.write(t + " ");
                }
                writer.close();

                System.out.println("Successfully wrote to " + i + ".txt");
            } catch (IOException e) {
                System.out.println("An error occurred at " + i + ".txt");
                e.printStackTrace();
            }
        }
    }


    public static void main(String[] args) {
        testN_toFile(15);
    }
}




