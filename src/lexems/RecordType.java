package lexems;

import java.util.ArrayList;

public class RecordType implements ILexem {
    public ArrayList<VariableDeclaration> variableDeclarations;

    public RecordType() {
        variableDeclarations = new ArrayList<>();
    }

    public void addDeclaration(VariableDeclaration declaration) {
        variableDeclarations.add(declaration);
    }
}
