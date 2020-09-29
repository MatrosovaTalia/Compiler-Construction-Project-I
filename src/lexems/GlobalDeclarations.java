package lexems;

import simple.ILexem;

import java.util.ArrayList;

public class GlobalDeclarations implements IList {
    public ArrayList<ILexem> globalDeclarations;

    public GlobalDeclarations() {
        globalDeclarations = new ArrayList<>();
    }

    @Override
    public void add(ILexem iLexem) {
        globalDeclarations.add(iLexem);
    }
}
