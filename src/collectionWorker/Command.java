package collectionWorker;

import fileManager.CollectionManager;
import fileManager.XmlToJava;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 The Command interface represents a command that can be executed by the application.
 */
public interface Command extends Serializable {
    CollectionManager collectionManager = new CollectionManager();
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
    XmlToJava XML_TO_JAVA = new XmlToJava();
    Map<String, Integer> pathCountMap = new HashMap<>();
    /**
     * Executes the command.
     */
    void execute();

    public static void updatePathCount(String path, Map<String, Integer> pathCountMap) {
        if (pathCountMap.containsKey(path)) {
            // If the path already exists in the map, increment its count
            int count = pathCountMap.get(path);
            pathCountMap.put(path, count + 1);
        } else {
            // If the path doesn't exist in the map, add it with a count of 1
            pathCountMap.put(path, 1);
        }
    }

}

