package simple;

import lexems.Expressions;
import simple.ILexem;

public class Print  implements ILexem {
    public Expressions expressions;

    public Print(Expressions expressions) {
        this.expressions = expressions;
    }
}
