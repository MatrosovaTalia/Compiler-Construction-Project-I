package lexems;

import java.math.BigInteger;

public class IntegerLiteral implements ILexem{
    public final BigInteger v;

    public IntegerLiteral(BigInteger v){
        this.v = v;
    }

    @Override
    public String toString() {
        return "IntegerLiteral{" +
                "v=" + v +
                '}';
    }
}
