package lexems;

public class IntegerLiteral implements ILexem{
    public final int v;

    public IntegerLiteral(int v){
        this.v = v;
    }

    @Override
    public String toString() {
        return "IntegerLiteral{" +
                "v=" + v +
                '}';
    }
}
