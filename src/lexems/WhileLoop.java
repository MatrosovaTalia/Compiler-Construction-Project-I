package lexems;

import simple.ILexem;

public class WhileLoop implements ILexem {
    public Expression expression;
    public Body body;

    public WhileLoop(Expression expression, Body body) {
        this.expression = expression;
        this.body = body;
    }
}
