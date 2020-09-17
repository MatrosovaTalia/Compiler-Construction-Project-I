package lexems;

import java.util.ArrayList;

public class IRelation implements ILexem {
    public ArrayList<Simple> simples;
    public ArrayList<RelationSign> relationSigns;
    public SummandSign summandSign = new SummandSign("+");
    public String not = null;

    public IRelation() {
        simples = new ArrayList<Simple>();
        relationSigns = new ArrayList<RelationSign>();
    }

    public void addSimple(Simple simple, RelationSign relationSign) {
        relationSigns.add(relationSign);
        simples.add(simple);
    }

    public void addSimple(Simple simple) {
        simples.add(simple);
    }

    public void setSummandSign(SummandSign summandSign) {
        this.summandSign = summandSign;
    }

    public void setNot() {
        this.not = "NOT";
    }
}
