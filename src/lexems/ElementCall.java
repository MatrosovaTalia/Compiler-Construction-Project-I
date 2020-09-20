package lexems;

import java.util.ArrayList;

public class ElementCall implements IList {
    public ArrayList<ILexem> elements;

    public ElementCall() {
        elements = new ArrayList<>();
    }

    public void add(ILexem iLexem) {
        elements.add(iLexem);
    }
}
