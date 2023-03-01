package collectionWorker;

import java.util.HashSet;

import fileManager.CollectionManager;
import fileManager.Command;
import model.Movie;

/**
 The ShowCommand class implements the Command interface and represents the "show" command.
 This command is used to display all the movies in the collection in a string format.
 */

public class ShowCommand implements Command {
    public static String info = "show command:\n" +
            "   This command will show u all collection in string format\n";
    private CollectionManager manager;

    /**
     * Constructs a new ShowCommand object with the given CollectionManager instance.
     *
     * @param manager The CollectionManager instance to use for managing the collection.
     */

    public ShowCommand(CollectionManager manager) {
        this.manager = manager;
    }

    /**
     * Executes the "show" command by getting all the movies in the collection and displaying them in a string format.
     */

    @Override
    public void execute() {
        HashSet<Movie> movies = manager.getMovies();
        if (movies.isEmpty()) {
            System.out.println("The collection is empty.");
            return;
        }
        StringBuilder sb = new StringBuilder();
        for (Movie movie : movies) {
            sb.append(movie.toString());
        }
        System.out.println(sb.toString());
    }
}