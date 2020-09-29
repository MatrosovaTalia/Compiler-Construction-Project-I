package lexems;

import simple.BooleanLiteral;
import simple.ILexem;
import simple.IntegerLiteral;
import simple.RealLiteral;

public class Primary implements ILexem {
    public ILexem primary;

    public Primary(IntegerLiteral integerLiteral) {
        primary = integerLiteral;
    }

    public Primary(RealLiteral realLiteral) {
        primary = realLiteral;
    }

    public Primary(BooleanLiteral booleanLiteral){
        primary = booleanLiteral;
    }

    public Primary(ModifiablePrimary modifiablePrimary) {
        primary = modifiablePrimary;
    }

    public Primary(RoutineCall routineCall) {
        primary = routineCall;
    }
}
