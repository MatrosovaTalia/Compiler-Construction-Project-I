public class Test {
    public static void main(String[] args) {
        Lexer lexer = new Lexer("tests/12.txt");
        for (Token t : lexer.lex()) {
            System.out.print(t + " ");
        }
    }
}




