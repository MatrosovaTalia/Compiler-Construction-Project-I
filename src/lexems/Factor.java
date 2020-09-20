package lexems;

import java.util.ArrayList;

public class Factor implements IList2 {
    public ArrayList<ILexem> summands;
    public ArrayList<ILexem> summandSigns;

    public Factor() {
        summands = new ArrayList<>();
        summandSigns = new ArrayList<>();
    }


    @Override
    public void add2(ILexem summand, ILexem summandSign) {
        summands.add(summand);
        summandSigns.add(summandSign);
    }

    @Override
    public void add(ILexem summand) {
        summands.add(summand);
    }
}
