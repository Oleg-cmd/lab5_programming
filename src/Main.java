import collectionWorker.ClearHistoryCommand;
import fileManager.CollectionManager;
import collectionWorker.ParseXmlCommand;
import fileManager.XmlToJava;
import helpers.UserInputHandler;

import java.io.*;
/**
 The main class for the application.
 */
public class Main {
    private static final String DEFAULT_COLLECTION_PATH = "./src/collection.xml";

    /**
     * The main method of the application. Initializes the command objects, collection manager, file reader and writer,
     * and starts the user input handling process.
     *
     * @param args command line arguments
     */
    public static void main(String[] args) {

        new ClearHistoryCommand().execute();
        CollectionManager collectionManager = new CollectionManager();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        UserInputHandler userInputHandler = new UserInputHandler(reader, writer, collectionManager);
        XmlToJava xmlToJava = new XmlToJava();

        try {
            String filePath;
            if (args.length > 0) {
                filePath = args[0];
            } else {
                writer.write("Enter the path of the XML file to load (press enter to use the default path '" + DEFAULT_COLLECTION_PATH + "'): ");
                writer.flush();
                filePath = reader.readLine();
                if (filePath.isEmpty()) {
                    filePath = DEFAULT_COLLECTION_PATH;
                }
            }

            ParseXmlCommand parseXmlCommand = new ParseXmlCommand(xmlToJava, collectionManager, filePath);
            parseXmlCommand.execute();
            writer.write("Collection loaded from file: " + filePath);
            writer.newLine();
            writer.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }

        userInputHandler.start();
    }
}