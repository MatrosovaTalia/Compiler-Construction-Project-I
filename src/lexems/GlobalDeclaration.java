package lexems;

import java.util.ArrayList;

public class GlobalDeclaration implements ILexem{
    public ILexem globalDeclaration;

    public GlobalDeclaration(SimpleDeclaration simpleDeclaration) {
        globalDeclaration = simpleDeclaration;
    }

    public GlobalDeclaration(RoutineDeclaration routineDeclaration) {
        globalDeclaration = routineDeclaration;
    }
}
