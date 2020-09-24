package lexer;

import misc.Pair;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;


public class Token {

    public static final HashMap<String, TokenType> KEYWORD_TABLE = initKeywordTable();
    private final String body;
    private final TokenType type;
    public final Pair<Integer, Integer> position;

    Token(String body, TokenType type, Pair<Integer, Integer> position) {
        this.body = body;
        this.type = type;
        this.position = position;
    }

    private static HashMap<String, TokenType> initKeywordTable() {
        HashMap<String, TokenType> result = new HashMap<>();
        TokenType[] arr = new TokenType[]{TokenType.VAR, TokenType.TYPE, TokenType.IS, TokenType.END, TokenType.INTEGER,
                TokenType.REAL, TokenType.BOOLEAN, TokenType.TRUE, TokenType.FALSE, TokenType.RECORD,
                TokenType.ARRAY, TokenType.WHILE, TokenType.LOOP, TokenType.FOR, TokenType.IN, TokenType.REVERSE,
                TokenType.IF, TokenType.THEN, TokenType.ELSE, TokenType.ROUTINE, TokenType.AND, TokenType.OR,
                TokenType.NOT, TokenType.XOR, TokenType.PRINT, TokenType.RETURN};

        try (BufferedReader reader = new BufferedReader(new FileReader("data/keywords.txt"))) {
            String line;
            int i = 0;
            while ((line = reader.readLine()) != null) {
                result.put(line, arr[i]);
                i += 1;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    public String getBody() {
        return body;
    }

    public TokenType getType() {
        return type;
    }

    public Pair<Integer, Integer> getPosition() {
        return position;
    }

    public TokenType getKeywordCode(String keyword) throws NoSuchFieldException {
        TokenType type = KEYWORD_TABLE.get(keyword);
        if (type != null) {
            return type;
        } else {
            throw new NoSuchFieldException("There is no such keyword in the table!");
        }
    }

    @Override
    public String toString() {
        return "{'" +
                (body.equals("\n") ? "\\n" : body)
                + "' :" +
                type.toString() + "("+ type.getValue() +")" + ", position: [" + position.key() + " " + position.value() + "]}"
                + (body.equals("\n") ? "\n" : "");
    }

}
