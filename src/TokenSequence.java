import java.util.ArrayList;
import java.util.Iterator;

public class TokenSequence implements Iterable<Token> {
    private final ArrayList<Token> arr;

    public TokenSequence() {
        this.arr = new ArrayList<>();
    }

    public Token get(int i) {
        return arr.get(i);
    }

    public void add(Token t) {
        arr.add(t);
    }

    @Override
    public Iterator<Token> iterator() {
        return arr.iterator();
    }
}

