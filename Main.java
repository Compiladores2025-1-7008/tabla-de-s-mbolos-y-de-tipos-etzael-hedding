import java.util.ArrayList;
import java.util.Optional;

public class Main {
    public static void main(String[] args) {
        // Crear una pila de tablas de símbolos
        SymbolTableStackImpl symbolTableStack = new SymbolTableStackImpl();

        // Crear una primera tabla de símbolos y añadirla a la pila
        SymbolTable globalTable = new SymbolTable();
        symbolTableStack.push(globalTable);

        // Crear un símbolo y agregarlo a la tabla de símbolos global
        ArrayList<Integer> args1 = new ArrayList<>();
        args1.add(1); args1.add(2);
        Symbol symbol1 = new SymbolImpl(100, 1, "variable", args1);
        globalTable.addSymbol("x", symbol1);

        // Comprobar si el símbolo fue añadido correctamente en la tabla global
        Optional<SymbolTable> lookupResult = symbolTableStack.lookup("x");
        System.out.println("Símbolo 'x' encontrado en tabla global: " + lookupResult.isPresent()); // Esperado: true

        // Crear una segunda tabla de símbolos (ej. para un nuevo bloque o función) y añadirla a la pila
        SymbolTable localTable = new SymbolTable();
        symbolTableStack.push(localTable);

        // Crear otro símbolo en la tabla local
        ArrayList<Integer> args2 = new ArrayList<>();
        Symbol symbol2 = new SymbolImpl(200, 2, "function", args2);
        localTable.addSymbol("y", symbol2);

        // Comprobar si el símbolo fue añadido correctamente en la tabla local
        Optional<Symbol> localSymbolLookup = localTable.getSymbol("y");
        System.out.println("Símbolo 'y' encontrado en tabla local: " + localSymbolLookup.isPresent()); // Esperado: true

        // Comprobar búsqueda en la pila (debería encontrar 'x' en la tabla global)
        lookupResult = symbolTableStack.lookup("x");
        System.out.println("Símbolo 'x' encontrado en la pila: " + lookupResult.isPresent()); // Esperado: true

        // Pop en la pila y verificar que 'y' ya no está en la cima
        symbolTableStack.pop();
        lookupResult = symbolTableStack.lookup("y");
        System.out.println("Símbolo 'y' después de pop: " + lookupResult.isPresent()); // Esperado: false

        // Prueba de la tabla de tipos
        TypeTableImpl typeTable = new TypeTableImpl();

        // Agregar un tipo sin estructura padre
        int typeId1 = typeTable.addType("int", 1, -1);
        System.out.println("Tipo 'int' añadido con ID: " + typeId1);

        // Agregar un tipo con una tabla de símbolos como estructura padre
        int typeId2 = typeTable.addType("struct MyStruct", globalTable);
        System.out.println("Tipo 'struct MyStruct' añadido con ID: " + typeId2);

        // Obtener información sobre los tipos añadidos
        Optional<Type> type1 = typeTable.getType(typeId1);
        System.out.println("Nombre del tipo con ID " + typeId1 + ": " + type1.map(Type::getName).orElse("No encontrado")); // Esperado: "int"

        Optional<Type> type2 = typeTable.getType(typeId2);
        System.out.println("Nombre del tipo con ID " + typeId2 + ": " + type2.map(Type::getName).orElse("No encontrado")); // Esperado: "struct MyStruct"
    }
}
