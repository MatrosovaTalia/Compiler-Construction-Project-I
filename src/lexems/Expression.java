package lexems;

import java.util.ArrayList;

public class Expression implements ILexem {
    public ArrayList<IRelation> relations;
    public ArrayList<LogicWord> logicWords;

    public Expression() {
        relations = new ArrayList<>();
        logicWords = new ArrayList<>();
    }

    public void addRelation(IRelation relation, LogicWord logicWord) {
        this.relations.add(relation);
        this.logicWords.add(logicWord);
    }

}
