package simple;

import simple.IExpression;

public class BooleanLiteral implements IExpression {
    public final boolean v;

    public BooleanLiteral(boolean v) {
        this.v = v;
    }

    @Override
    public String toString() {
        return "BooleanLiteral{" +
                "v=" + v +
                '}';
    }
}
