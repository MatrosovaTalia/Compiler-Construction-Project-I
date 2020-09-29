package lexems;

import simple.ILexem;

import java.util.ArrayList;

public class Simple implements IList2 {
    public ArrayList<ILexem> factors;
    public ArrayList<ILexem> factorSigns;

    public Simple() {
        factors = new ArrayList<>();
        factorSigns = new ArrayList<>();
    }


    @Override
    public void add2(ILexem iLexem1, ILexem iLexem2) {
        factors.add(iLexem1);
        factorSigns.add(iLexem2);
    }

    @Override
    public void add(ILexem iLexem) {
        factors.add(iLexem);
    }
}
