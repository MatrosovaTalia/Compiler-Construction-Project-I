package lexems;

import simple.ILexem;

public class Assignment implements ILexem {
    public ModifiablePrimary modifiablePrimary;
    public Expression expression;

    public Assignment(ModifiablePrimary modifiablePrimary, Expression expression) {
        this.modifiablePrimary = modifiablePrimary;
        this.expression = expression;
    }
}
