package collectionWorker;


/**
 A command that parses an XML file and adds the movies it contains to a collection.
 */
public class ParseXmlCommand implements Command {
    private String fileName;

    /**
     * Constructs a new instance of the {@code ParseXmlCommand} class with the specified XML to Java parser,
     * collection manager, and file name.
     *
     * @param fileName the name of the XML file to parse
     */

    public ParseXmlCommand(String fileName) {
        this.fileName = fileName;
    }

    /**
     * Parses the XML file specified by the file name and adds the movies it contains to the collection using
     * the collection manager.
     */

    @Override
    public void execute() {
        collectionManager.addAllMovies(XML_TO_JAVA.parseXml(fileName));
    }
}