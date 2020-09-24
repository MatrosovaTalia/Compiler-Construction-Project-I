package lexer;

import misc.Pair;

public class Position {
    private final int line;
    private final int pos;

    public int getLine() {
        return line;
    }

    public int getPos() {
        return pos;
    }

    public Position(int line, int pos) {
        this.line = line;
        this.pos = pos;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Position) {
            return ((Position) obj).line == this.line &&
                    ((Position) obj).pos == this.pos;
        }
        else {
            return false;
        }
    }

    @Override
    public String toString() {
        return "< line:" + line + ", position: " + pos + ">";
    }
}
