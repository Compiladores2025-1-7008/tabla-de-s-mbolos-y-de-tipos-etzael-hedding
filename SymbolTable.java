import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Stack;

public class SymbolTable {
    private Map<String, Symbol> symbols = new HashMap<>();

    public void addSymbol(String id, Symbol symbol) {
        symbols.put(id, symbol);
    }

    public Optional<Symbol> getSymbol(String id) {
        return Optional.ofNullable(symbols.get(id));
    }
}