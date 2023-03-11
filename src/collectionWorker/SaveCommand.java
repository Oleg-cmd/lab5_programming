package collectionWorker;

import fileManager.XmlConverter;

import javax.xml.parsers.ParserConfigurationException;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 A command that saves a collection of movies to an XML file.
 */
public class SaveCommand implements Command {
    public static String info = "save command:\n" +
            "   This command will save your collection to xml file\n";
    private final String fileName;

    /**
     * Creates a new SaveCommand with the specified file name and movie collection.
     *
     * @param fileName the name of the file to which the collection should be saved
     */

    public SaveCommand(String fileName) {
        this.fileName = fileName;
    }

    /**
     * Saves the collection of movies to an XML file with the specified file name.
     */
    @Override
    public void execute(){
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            XmlConverter.convertToXml(collectionManager.getMovies(), writer);
            System.out.println("Movies saved to file: " + fileName);
        } catch (IOException e) {
            System.err.println("Error saving movies to file: " + fileName + ", " + e.getMessage());
        } catch (ParserConfigurationException e) {
            System.out.println(e);
        }
    }
}
