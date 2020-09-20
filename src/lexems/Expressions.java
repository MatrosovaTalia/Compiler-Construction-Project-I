package lexems;

import java.util.ArrayList;

public class Expressions implements IList {
    public ArrayList<ILexem> expressions;

    public Expressions(Expression expression) {
        expressions.add(expression);
    }

    @Override
    public void add(ILexem iLexem) {
        expressions.add(iLexem);
    }
}
