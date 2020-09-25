package simple;

import lexems.Identifier;

import java.util.ArrayList;

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
        return "Routine Declaration{" +
                "name=" + name + '\'' +
                "return type=" + returnType +'\''+
                "parameters" + params + '\'' +
                "body=" + body + '\'' +
                '}';
    }
}
