package collectionWorker;

import java.io.File;
import java.io.IOException;


/**
 The InfoCommand class implements the Command interface and represents the "info" command that prints
 information about the collection, such as the type, size, and path of the collection file, and the number
 of models in the collection.
 */
public class InfoCommand implements Command {
    public static String info = "info command:\n" +
            "   This command will print all info about collection (type, size, etc)\n";


    /**
     * Creates a new instance of the InfoCommand class with the specified HashSet of models.
     */
    public InfoCommand() {

    }

    /**
     * Executes the "info" command by printing information about the collection, such as the type, size, and path of the
     * collection file, and the number of models in the collection.
     */

    @Override
    public void execute(){
        File file = new File("./src/collection.xml");
        try{
            writer.write("Type of myCollection: HashSet<Movie>\n");
            writer.write("Type of models in myCollection: Movie\n");
            writer.write("Number of models in myCollection: " + collectionManager.getMovies().size() + "\n");
            writer.write("Name: "+ file.getName()+ "\n");
            writer.write("Path: "+ file.getAbsolutePath()+ "\n");
            writer.write("Size: "+ file.length()+ "\n");
            writer.write("Writeable: "+ file.canWrite()+ "\n");
            writer.write("Readable: "+ file.canRead()+ "\n");
        }catch (IOException e){
            System.out.println(e);
        }


    }

}


