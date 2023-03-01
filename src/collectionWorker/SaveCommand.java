package collectionWorker;

import fileManager.Command;
import fileManager.XmlConverter;
import model.Movie;

import javax.xml.parsers.ParserConfigurationException;
import java.io.*;
import java.util.HashSet;

/**
 A command that saves a collection of movies to an XML file.
 */
public class SaveCommand implements Command {
    public static String info = "save command:\n" +
            "   This command will save your collection to xml file\n";
    private final String fileName;
    private final HashSet<Movie> movies;

    /**
     * Creates a new SaveCommand with the specified file name and movie collection.
     *
     * @param fileName the name of the file to which the collection should be saved
     * @param movies the collection of movies to be saved
     */

    public SaveCommand(String fileName, HashSet<Movie> movies) {
        this.fileName = fileName;
        this.movies = movies;
    }

    /**
     * Saves the collection of movies to an XML file with the specified file name.
     */
    @Override
    public void execute(){
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            XmlConverter.convertToXml(movies, writer);
            System.out.println("Movies saved to file: " + fileName);
        } catch (IOException e) {
            System.err.println("Error saving movies to file: " + fileName + ", " + e.getMessage());
        } catch (ParserConfigurationException e) {
            throw new RuntimeException(e);
        }
    }
}
