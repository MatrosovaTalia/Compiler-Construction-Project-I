package lexems;

public class Statement implements ILexem {
    public ILexem statement;
    public Statement(Assignment assignment) {
        statement = assignment;
    }

    public Statement(RoutineCall routineCall) {
        statement = routineCall;
    }

    public Statement(WhileLoop whileLoop) {
        statement = whileLoop;
    }

    public Statement(ForLoop forLoop) {
        statement = forLoop;
    }

    public Statement(IfStatement ifStatement){
        statement = ifStatement;
    }

    public Statement(Print print) {
        statement = print;
    }
}
