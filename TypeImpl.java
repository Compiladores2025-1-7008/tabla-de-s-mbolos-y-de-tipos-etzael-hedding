import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class TypeImpl implements Type {
    private String name;
    private short items;
    private short tam;
    private int parentId;
    private SymbolTable parentStruct;

    public TypeImpl(String name, short items, short tam, int parentId, SymbolTable parentStruct) {
        this.name = name;
        this.items = items;
        this.tam = tam;
        this.parentId = parentId;
        this.parentStruct = parentStruct;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public short getItems() {
        return items;
    }

    @Override
    public short getTam() {
        return tam;
    }

    @Override
    public int getParenId() {
        return parentId;
    }

    @Override
    public SymbolTable getParentStruct() {
        return parentStruct;
    }
}

