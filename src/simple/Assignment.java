package simple;

public class Assignment implements IStatement{
    ModifiablePrimary m_primary;
    IExpression expression;

    public Assignment(ModifiablePrimary m_primary, IExpression expression){
        this.m_primary = m_primary;
        this.expression = expression;
    }

    @Override
    public String toString() {
        return "\nAssignment{" +
                "primary=" + m_primary + '\'' +
                "expression=" + expression + '\'' +
                '}';
    }
}
