package lexems;

import java.util.ArrayList;

public class ModifiablePrimary implements ILexem {
    public Identifier identifier;
    public ILexem elementCall;

    public ModifiablePrimary(Identifier identifier, ILexem elementCall) {
        this.identifier = identifier;
        this.elementCall = elementCall;
    }
}
