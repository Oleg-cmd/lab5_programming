package collectionWorker;

import fileManager.CollectionManager;
import fileManager.Command;
import fileManager.XmlToJava;

/**
 A command that parses an XML file and adds the movies it contains to a collection.
 */
public class ParseXmlCommand implements Command {
    private XmlToJava xmlToJava;
    private CollectionManager collectionManager;
    private String fileName;

    /**
     * Constructs a new instance of the {@code ParseXmlCommand} class with the specified XML to Java parser,
     * collection manager, and file name.
     *
     * @param xmlToJava the XML to Java parser to use to parse the XML file
     * @param collectionManager the collection manager to use to add the movies to the collection
     * @param fileName the name of the XML file to parse
     */

    public ParseXmlCommand(XmlToJava xmlToJava, CollectionManager collectionManager, String fileName) {
        this.xmlToJava = xmlToJava;
        this.collectionManager = collectionManager;
        this.fileName = fileName;
    }

    /**
     * Parses the XML file specified by the file name and adds the movies it contains to the collection using
     * the collection manager.
     */

    @Override
    public void execute() {
        collectionManager.addAllMovies(xmlToJava.parseXml(fileName));
    }
}