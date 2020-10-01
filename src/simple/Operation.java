package simple;

public enum Operation {
    PLUS(1),
    MINUS(2),
    MULTIPLY(3),
    DIVIDE(4),
    REMAINDER(5),
    AND(6),
    OR(7),
    XOR(8),
    LESS(9),
    GREATER(10),
    LEQUALS(11),
    GEQUALS(12),
    EQUALS(13),
    NEQUALS(14);


    int value;

    Operation(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
