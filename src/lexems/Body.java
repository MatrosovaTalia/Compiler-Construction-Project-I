package lexems;

import java.util.ArrayList;

public class Body implements ILexem {
    public ArrayList<BodyDeclaration> bodyDeclarations;

    public Body() {
        bodyDeclarations = new ArrayList<>();
    }

    public void addBody(BodyDeclaration bodyDeclaration) {
        bodyDeclarations.add(bodyDeclaration);
    }
}
