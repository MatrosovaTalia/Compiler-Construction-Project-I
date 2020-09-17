package lexems;

public class RealLiteral implements ILexem {
    public final double v;

    public RealLiteral(double v){
        this.v = v;
    }

    @Override
    public String toString() {
        return "RealLiteral{" +
                "v=" + v +
                '}';
    }
}
