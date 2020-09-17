package lexems;

import java.util.ArrayList;

public class ElementCall implements ILexem {
    public ArrayList<ILexem> elements;

    public ElementCall() {
        elements = new ArrayList<>();
    }

    public void addElementCall(Identifier identifier) {
        elements.add(identifier);
    }

    public void addElementCall(Expression expression) {
        elements.add(expression);
    }
}
