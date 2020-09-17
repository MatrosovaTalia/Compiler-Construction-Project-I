package lexems;

import java.util.ArrayList;

public class Parameters implements ILexem{
    public ArrayList<Parameter> parameters;

    public Parameters(Parameter parameter) {
        parameters = new ArrayList<>();
        parameters.add(parameter);
    }

    public void addParameter(Parameter parameter) {
        parameters.add(parameter);
    }
}
