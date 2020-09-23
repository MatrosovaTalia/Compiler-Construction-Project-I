package ast;

public class GlobalDeclarations extends ASTNode {
    int i = 0;

    GlobalDeclarations(ASTNode decl) {
        children.put(Integer.toString(i), decl);
    }

    void add(ASTNode decl) {
        i += 1;
        children.put(Integer.toString(i), decl);
    }
}
