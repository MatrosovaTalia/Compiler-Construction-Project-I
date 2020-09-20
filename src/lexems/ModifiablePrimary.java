package lexems;

import java.util.ArrayList;

public class ModifiablePrimary implements ILexem {
    public ILexem identifier;
    public ILexem elementCall;

    public ModifiablePrimary(ILexem identifier, ILexem elementCall) {
        this.identifier = identifier;
        this.elementCall = elementCall;
    }
}
