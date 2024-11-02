import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class TypeTableImpl implements TypeTable {
    private Map<Integer, Type> types = new HashMap<>();
    private int currentId = 0;

    @Override
    public int getTam(int id) {
        return types.get(id).getTam();
    }

    @Override
    public int getItems(int id) {
        return types.get(id).getItems();
    }

    @Override
    public String getName(int id) {
        return types.get(id).getName();
    }

    @Override
    public int getParenId(int id) {
        return types.get(id).getParenId();
    }

    @Override
    public SymbolTable getParentStruct(int id) {
        return types.get(id).getParentStruct();
    }

    @Override
    public Optional<Type> getType(int id) {
        return Optional.ofNullable(types.get(id));
    }

    @Override
    public int addType(String name, int items, int parent) {
        Type newType = new TypeImpl(name, (short) items, (short) 0, parent, null);
        types.put(currentId, newType);
        return currentId++;
    }

    @Override
    public int addType(String name, SymbolTable parent) {
        Type newType = new TypeImpl(name, (short) 0, (short) 0, -1, parent);
        types.put(currentId, newType);
        return currentId++;
    }
}
