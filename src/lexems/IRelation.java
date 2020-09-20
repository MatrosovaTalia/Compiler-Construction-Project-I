package lexems;

import java.util.ArrayList;

public class IRelation implements IList2 {
    public ArrayList<ILexem> simples;
    public ArrayList<ILexem> relationSigns;
    public SummandSign summandSign = new SummandSign("+");
    public String not = null;

    public IRelation() {
        simples = new ArrayList<>();
        relationSigns = new ArrayList<>();
    }

    public void setSummandSign(SummandSign summandSign) {
        this.summandSign = summandSign;
    }

    public void setNot() {
        this.not = "NOT";
    }

    @Override
    public void add2(ILexem iLexem1, ILexem iLexem2) {
        relationSigns.add(iLexem1);
        simples.add(iLexem2);
    }

    @Override
    public void add(ILexem iLexem) {
        simples.add(iLexem);
    }
}
