package collectionWorker;

import java.io.File;
import java.util.HashSet;


import fileManager.Command;
import model.Movie;

/**
 The InfoCommand class implements the Command interface and represents the "info" command that prints
 information about the collection, such as the type, size, and path of the collection file, and the number
 of models in the collection.
 */
public class InfoCommand implements Command {
    public static String info = "info command:\n" +
            "   This command will print all info about collection (type, size, etc)\n";
    private final HashSet<Movie> myCollection;

    /**
     * Creates a new instance of the InfoCommand class with the specified HashSet of models.
     *
     * @param myCollection the HashSet that contains all the models in the collection.
     */
    public InfoCommand(HashSet<Movie> myCollection) {
        this.myCollection = myCollection;
    }

    /**
     * Executes the "info" command by printing information about the collection, such as the type, size, and path of the
     * collection file, and the number of models in the collection.
     */

    @Override
    public void execute() {
        File file = new File("./src/collection.xml");
        System.out.println("Type of myCollection: HashSet<Movie>");
        System.out.println("Type of models in myCollection: Movie");
        System.out.println("Number of models in myCollection: " + myCollection.size());
        System.out.println("Name: "+ file.getName());
        System.out.println("Path: "+ file.getAbsolutePath());
        System.out.println("Size: "+ file.length());
        System.out.println("Writeable: "+ file.canWrite());
        System.out.println("Readable: "+ file.canRead());

    }

}


