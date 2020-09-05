package lexer;

public class CharacterBuffer {
    private final StringBuilder internalStorage;

    CharacterBuffer() {
        internalStorage = new StringBuilder();
    }

    public void add(int c) {
        internalStorage.append((char) c);
    }

    public void flush() {
        internalStorage.delete(0, internalStorage.length());
    }

    @Override
    public String toString() {
        return internalStorage.toString();
    }
}
