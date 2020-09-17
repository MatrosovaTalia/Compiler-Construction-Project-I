package lexems;

import java.util.ArrayList;

public class Factor implements ILexem {
    public ArrayList<Summand> summands;
    public ArrayList<SummandSign> summandSigns;

    public Factor() {
        summands = new ArrayList<>();
        summandSigns = new ArrayList<>();
    }

    public void addSummand(Summand summand, SummandSign summandSign) {
        summands.add(summand);
        summandSigns.add(summandSign);
    }

    public void addSummand(Summand summand) {
        summands.add(summand);
    }
}
