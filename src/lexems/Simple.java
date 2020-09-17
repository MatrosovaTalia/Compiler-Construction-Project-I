package lexems;

import java.util.ArrayList;

public class Simple implements ILexem {
    public ArrayList<Factor> factors;
    public ArrayList<FactorSign> factorSigns;

    public Simple() {
        factors = new ArrayList<>();
        factorSigns = new ArrayList<>();
    }

    public void addFactor(Factor factor, FactorSign factorSign) {
        factors.add(factor);
        factorSigns.add(factorSign);
    }

    public void addFactor(Factor factor) {
        factors.add(factor);
    }
}
