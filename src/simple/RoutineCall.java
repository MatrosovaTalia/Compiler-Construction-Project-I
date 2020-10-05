package simple;


public class RoutineCall implements IStatement {
    Identifier id;
    Expressions expressions;

    public RoutineCall(Identifier id, Expressions expressions){
        this.expressions = expressions;
        this.id = id;
    }

    @Override
    public String toString() {
        return "\n    Routine_call{" +
                "id='" + id + '\'' +
                "Parameters " + expressions + '\'' +
                '}';
    }
}
