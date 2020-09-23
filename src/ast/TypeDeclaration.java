package ast;

public class TypeDeclaration extends ASTNode {
    TypeDeclaration(ASTNode name, ASTNode type) {
        children.put("type_name", name);
        children.put("type", type);
    }

    @Override
    void add(ASTNode node) {

    }
}
