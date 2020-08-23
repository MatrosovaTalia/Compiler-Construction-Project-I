import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;

public class BufferedReaderWithPosition extends BufferedReader {
    private int line = 1, pos = 1;

    public BufferedReaderWithPosition(Reader in) {
        super(in);
    }
    @Override
    public int read() throws IOException {
        int c = super.read();
        if (c == '\n') {
            line += 1;
            pos = 0;
        }
        else {
            pos += 1;
        }
        return c;
    }

    public int line() {
        return line;
    }

    public int pos() {
        return pos;
    }
}
