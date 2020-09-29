package simple;

public enum Operation {
    PLUS(1),
    MINUS(2),
    MULTIPLY(3),
    DIVIDE(4),
    REMAINDER(5);


    int value;

    Operation(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
