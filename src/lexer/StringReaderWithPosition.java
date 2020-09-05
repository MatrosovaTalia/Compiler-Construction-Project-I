package lexer;

public class StringReaderWithPosition {
    private int line = 1, pos = 0, i = 0;
    private String in;

    public StringReaderWithPosition(String in) {
        this.in = in;
    }

    public int read() {
        int c = -1;
        if (i < in.length()) {
            c = in.charAt(i);
            if (i > 1 && in.charAt(i-1) == '\n') {
                line += 1;
                pos = 1;
            } else {
                pos += 1;
            }
        }
        i += 1;
        return c;
    }

    public int line() {
        return line;
    }

    public int pos() {
        return pos;
    }

    public void close() {
        in = null;
    }
}
