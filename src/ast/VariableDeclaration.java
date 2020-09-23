package ast;


public class VariableDeclaration extends ASTNode {
    VariableDeclaration(ASTNode name, ASTNode type, ASTNode value) {
        children.put("variable_name", name);
        children.put("variable_type", type);
        children.put("variable_value", value);
    }

    @Override
    void add(ASTNode node) {

    }
}
