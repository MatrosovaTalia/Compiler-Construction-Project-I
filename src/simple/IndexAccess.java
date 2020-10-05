package simple;

public class IndexAccess implements IPrimary{
    IExpression expression;

    public IndexAccess(IExpression expression){
        this.expression = expression;
    }

    @Override
    public String toString() {
        return "\nIndex_Access{" +
                "expression=" + expression + '\'' +
                '}';
    }
}
