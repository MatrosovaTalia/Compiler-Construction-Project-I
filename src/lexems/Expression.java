package lexems;

import java.util.ArrayList;

public class Expression implements IList2 {
    public ArrayList<ILexem> relations;
    public ArrayList<ILexem> logicWords;

    public Expression() {
        relations = new ArrayList<>();
        logicWords = new ArrayList<>();
    }

    @Override
    public void add2(ILexem iLexem1, ILexem iLexem2) {
        this.relations.add(iLexem1);
        this.logicWords.add(iLexem2);
    }

    @Override
    public void add(ILexem iLexem) {
        this.relations.add(iLexem);
    }
}
