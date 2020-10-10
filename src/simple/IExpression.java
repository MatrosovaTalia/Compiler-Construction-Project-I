package simple;

public interface IExpression extends ILexem {
    Object resolve_value();
    String resolve_type(String methodName);
}
