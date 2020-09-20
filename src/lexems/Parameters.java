package lexems;

import java.util.ArrayList;

public class Parameters implements IList{
    public ArrayList<ILexem> parameters;

    public Parameters(Parameter parameter) {
        parameters = new ArrayList<>();
        parameters.add(parameter);
    }

    @Override
    public void add(ILexem iLexem) {
        parameters.add(iLexem);
    }
}
