package collectionWorker;

import fileManager.CollectionManager;
import fileManager.XmlToJava;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 The Command interface represents a command that can be executed by the application.
 */
public interface Command {
    CollectionManager collectionManager = new CollectionManager();
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
    XmlToJava XML_TO_JAVA = new XmlToJava();
    /**
     * Executes the command.
     */
    void execute();


}

