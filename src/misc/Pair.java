package misc;

public class Pair<K, V> {
    private K key; // first element of pair
    private V value; // second element of pair

    public Pair(K key, V value) {
        this.key = key;
        this.value = value;
    }

    public void setKey(K key) {
        this.key = key;
    }

    public void setValue(V value) {
        this.value = value;
    }

    public K key() {
        return key;
    }

    public V value() {
        return value;
    }
}