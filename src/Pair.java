public class Pair<C1, C2> {
    private C1 element_1; // first element of pair
    private C2 element_2; // second element of pair

    public Pair(C1 element_1, C2 element_2) {
        this.element_1 = element_1;
        this.element_2 = element_2;
    }

    public void set_1(C1 element_1) {
        this.element_1 = element_1;
    }

    public void set_2(C2 element_2) {
        this.element_2 = element_2;
    }

    public C1 get_1() {
        return element_1;
    }

    public C2 get_2() {
        return element_2;
    }
}