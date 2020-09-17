package lexems;

import java.util.ArrayList;

public class Expressions implements ILexem {
    public ArrayList<Expression> expressions;

    public Expressions(Expression expression) {
        expressions = new ArrayList<>();
        expressions.add(expression);
    }

    public void addExpression(Expression expression) {
        expressions.add(expression);
    }
}
