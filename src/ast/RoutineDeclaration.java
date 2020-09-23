package ast;

import java.util.TreeMap;

public class RoutineDeclaration extends ASTNode {
    RoutineDeclaration(ASTNode name, ASTNode params, ASTNode body, ASTNode returnType) {
        children.put("routine_name", name);
        children.put("routine_params", params);
        children.put("routine_body", body);
        children.put("return_type", returnType);
    }

    @Override
    void add(ASTNode node) {

    }
}
