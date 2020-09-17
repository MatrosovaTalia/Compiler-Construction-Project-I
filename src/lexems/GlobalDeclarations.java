package lexems;

import java.util.ArrayList;

public class GlobalDeclarations implements ILexem {
    public ArrayList<GlobalDeclaration> globalDeclarations;

    public GlobalDeclarations() {
        globalDeclarations = new ArrayList<>();
    }

    public void addDeclaration(GlobalDeclaration globalDeclaration) {
        globalDeclarations.add(globalDeclaration);
    }
}
