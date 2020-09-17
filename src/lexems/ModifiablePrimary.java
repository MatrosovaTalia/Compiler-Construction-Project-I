package lexems;

import java.util.ArrayList;

public class ModifiablePrimary implements ILexem {
    public Identifier identifier;
    public ElementCall elementCall;

    public ModifiablePrimary(Identifier identifier, ElementCall elementCall) {
        this.identifier = identifier;
        this.elementCall = elementCall;
    }
}
