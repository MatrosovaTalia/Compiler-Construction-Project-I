package simple;

import java.util.HashMap;

public class SymbolTable {
    private HashMap<String, String> table;

    SymbolTable() {
        table = new HashMap<>();
    }

    void put(String id, String type) {
        table.put(id, type);
    }

    String get(String id) {
        return table.get(id);
    }
}
