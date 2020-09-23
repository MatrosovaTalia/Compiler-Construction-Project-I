package ast;

import java.util.ArrayList;
import java.util.TreeMap;


public abstract class ASTNode {

    TreeMap<String, ASTNode> children = new TreeMap<>();
    abstract void add(ASTNode node);

}
