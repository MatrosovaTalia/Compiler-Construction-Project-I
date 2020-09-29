package simple;

import simple.ILexem;

public class Identifier implements ILexem {
    public String v;

    public Identifier(String v) {
        this.v = v;
    }

    @Override
    public String toString() {
        return "Identifier{" +
                "id=" + v + '\'' +
                '}';
    }
}
