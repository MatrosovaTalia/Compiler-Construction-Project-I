package simple;

public class RoutineDeclaration implements IDeclaration {
    Identifier name;
    Parameters params;
    IType returnType;
    Body body;


    public RoutineDeclaration(Identifier name, Parameters params, IType returnType, Body body){
        this.name = name;
        this.params = params;
        this.returnType = returnType;
        this.body = body;
    }

    @Override
    public String toString() {
        return "\nRoutine Declaration{" +
                "name=" + name + '\'' +
                "return type=" + returnType +'\''+
                "parameters" + params + '\'' +
                "body=" + body + '\'' +
                '}';
    }
}
