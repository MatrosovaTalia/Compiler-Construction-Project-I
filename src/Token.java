import java.io.*;
import java.util.HashMap;

enum TokenType {
    KEYWORD, IDENTIFIER, INTEGER, REAL, SYMBOLIC, SEPARATOR
}


public class Token {

    private final String body;
    private final TokenType type;
    private final int code;
    public static final HashMap<String, Integer> KEYWORD_TABLE = initKeywordTable();
    private final Pair<Integer, Integer> position;

    Token(String body, TokenType type, int code, Pair<Integer, Integer> position) {
        this.body = body;
        this.type = type;
        this.code = code;
        this.position = position;
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

    public int getCode() {
        return code;
    }

    public int getKeywordCode(String keyword) throws NoSuchFieldException {
        Integer res = KEYWORD_TABLE.get(keyword);
        if (res != null) {
            return res;
        } else {
            throw new NoSuchFieldException("There is no such keyword in the table!");
        }
    }

    private static HashMap<String, Integer> initKeywordTable() {
        HashMap<String, Integer> result = new HashMap<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("data/keywords.txt"))) {
            String line;
            int i = 1;
            while ((line = reader.readLine()) != null) {
                result.put(line, i);
                i += 1;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public String toString() {
        return body + type;
//        return "{'" + (body.equals("\n") ? "\\n" : body) + "' :" + type.toString() + "}";
    }

}
