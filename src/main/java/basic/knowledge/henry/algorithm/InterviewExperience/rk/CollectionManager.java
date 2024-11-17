package basic.knowledge.henry.algorithm.InterviewExperience.rk;

import java.util.*;

public class CollectionManager {
    private final Set<String> collection = new HashSet<>();
    private final Stack<Operation> undoStack = new Stack<>();
    private final Stack<Operation> redoStack = new Stack<>();

    // Adds a string to the collection
    public void add(String s) {
        if (collection.add(s)) {
            undoStack.push(new Operation("add", s));
            redoStack.clear(); // Clear redo stack after a new operation
        }
    }

    // Removes a string from the collection
    public void remove(String s) {
        if (collection.remove(s)) {
            undoStack.push(new Operation("remove", s));
            redoStack.clear(); // Clear redo stack after a new operation
        }
    }

    // Checks if a string exists in the collection
    public boolean contains(String s) {
        return collection.contains(s);
    }

    // Undo the last operation
    //undo 需要做两件事。 1 更新collection
    //2 更新redo
    public void undo() {
        if (!undoStack.isEmpty()) {
            Operation op = undoStack.pop();
            redoStack.push(op);

            switch (op.type) {
                case "add":
                    collection.remove(op.value);
                    break;
                case "remove":
                    collection.add(op.value);
                    break;
            }
        }
    }

    // Redo the most recently undone operation
    public void redo() {
        if (!redoStack.isEmpty()) {
            Operation op = redoStack.pop();
            undoStack.push(op);

            switch (op.type) {
                case "add":
                    collection.add(op.value);
                    break;
                case "remove":
                    collection.remove(op.value);
                    break;
            }
        }
    }

    // Search for strings starting with a given prefix
    public List<String> search(String prefix) {
        List<String> result = new ArrayList<>();
        for (String s : collection) {
            if (s.startsWith(prefix)) {
                result.add(s);
            }
        }
        return result;
    }

    // Helper class to store operations
    private static class Operation {
        String type; // "add" or "remove"
        String value;

        Operation(String type, String value) {
            this.type = type;
            this.value = value;
        }
    }

    // Main method for testing
    public static void main(String[] args) {
        CollectionManager manager = new CollectionManager();

        manager.add("apple");
        manager.add("banana");
        manager.add("apricot");


        System.out.println(manager.contains("banana") + " 1"); // true
        manager.remove("banana");

        System.out.println(manager.contains("banana")+ " 4"); // false
        manager.undo();
        System.out.println(manager.contains("banana")+ " 5"); // true

        manager.redo();
        System.out.println(manager.contains("banana")+ " 6"); // false

        manager.undo();
        System.out.println(manager.contains("banana")+ " 7"); // true

        System.out.println(manager.search("ap")); // ["apple", "apricot"]
    }
}
