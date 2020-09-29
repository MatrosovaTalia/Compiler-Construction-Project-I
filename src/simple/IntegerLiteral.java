package simple;


import java.math.BigInteger;

public class IntegerLiteral implements IExpression {
    public final BigInteger v;

    public IntegerLiteral(BigInteger v){
        this.v = v;
    }

    @Override
    public String toString() {
        return "IntegerLiteral{" +
                "int_value=" + v +
                '}';
    }
}
