package collectionWorker;

import fileManager.CollectionManager;
import fileManager.Command;
import model.Movie;

import java.util.HashSet;

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

    private CollectionManager manager;

    /**
     * Constructs a ClearCommand with a CollectionManager.
     *
     * @param manager the CollectionManager instance to be used by the command
     */
    public ClearCommand(CollectionManager manager) {
        this.manager = manager;
    }

    /**
     * Executes the command to clear all movies in the collection.
     */
    @Override
    public void execute() {
        HashSet<Movie> movies = manager.getMovies();

        if (movies.isEmpty()) {
            System.out.println("The collection is already empty.");
            return;
        }

        movies.clear();
        System.out.println("All movies have been successfully removed from the collection.");
    }
}