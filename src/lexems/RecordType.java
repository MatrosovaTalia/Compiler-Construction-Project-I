package lexems;

import simple.ILexem;

import java.util.ArrayList;

public class RecordType implements IList {
    public ArrayList<ILexem> variableDeclarations;

    public RecordType() {
        variableDeclarations = new ArrayList<>();
    }

    @Override
    public void add(ILexem iLexem) {
        variableDeclarations.add(iLexem);
    }
}
