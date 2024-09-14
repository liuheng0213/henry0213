package basic.knowledge.henry.algorithm.InterviewExperience.trade_desk;


import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

//https://leetcode.com/discuss/interview-question/279913/key-value-store-with-transaction
//backup
public class TransactionMap_nostack {
    Map<String, String> store;
    Map<String, String> tmpMap;//tmp storage

    TransactionMap_nostack() {
        store = new HashMap<>();
        //transactionalStack = new Stack<>();
    }

    void begin() {
        if(tmpMap != null){
            throw new RuntimeException("Stack is empty nothing to commit");
        }
        tmpMap = new HashMap<>();
    }

    /**
     * irrelated to tmpMap which is related to update operations
     * @param key
     * @return
     */
    String get(String key) {
        if (store.isEmpty() || !store.containsKey(key)) {
            return null;
        }

        return store.get(key);
    }

    void set(String key, String val) {
        if (tmpMap== null) { // not in the state of begin
            store.put(key, val);
            return;
        }

        tmpMap.put(key, val);
    }

    void commit() {
        if (tmpMap.isEmpty()) {
            throw new RuntimeException("Stack is empty nothing to commit");
        }

        store.putAll(tmpMap);
    }

    void rollback() {
        if (tmpMap.isEmpty()) {
            throw new RuntimeException("Nothing to rollback");
        }
        tmpMap = null;
    }

    void delete (String key) {
        // the state is in transaction
        store.remove(key);
        tmpMap.remove(key);
    }


}
