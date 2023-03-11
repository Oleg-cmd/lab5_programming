package collectionWorker;

import java.io.IOException;

/**
 * This command clears all movies in the collection.
 */
public class ClearCommand implements Command {
    /**
     * A brief description of the command.
     */
    public static String info = "clear command:\n" +
            "   This command will clear all movies in the collection.\n" +
            "   Note: there is no auto-save to file.";



    /**
     * Constructs a ClearCommand with a CollectionManager.
     */
    public ClearCommand() {

    }

    /**
     * Executes the command to clear all movies in the collection.
     */
    @Override
    public void execute() {
        try{
            if (collectionManager.getMovies().isEmpty()) {
                writer.write("The collection is already empty.");
                return;
            }else {
                collectionManager.clearMovies();
            }

            writer.write("All movies have been successfully removed from the collection.");
        }catch (IOException e ){
            System.out.println(e);
        }




    }
}