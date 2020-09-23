package lexems;

import java.math.BigDecimal;

public class RealLiteral implements ILexem {
    public final BigDecimal v;

    public RealLiteral(BigDecimal v){
        this.v = v;
    }

    @Override
    public String toString() {
        return "RealLiteral{" +
                "v=" + v +
                '}';
    }
}
