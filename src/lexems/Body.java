package lexems;

import java.util.ArrayList;

public class Body implements ILexem {
    public ArrayList<ILexem> bodyDeclarations;

    public Body() {
        bodyDeclarations = new ArrayList<>();
    }

    public void addBody(ILexem bodyDeclaration) {
        bodyDeclarations.add(bodyDeclaration);
    }
}
