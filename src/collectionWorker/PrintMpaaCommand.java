package collectionWorker;

import model.Movie;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 This class implements the Command interface to print the collection to console in order of mpaa rating.
 NC_17 > R > PG_13 > PG
 */
public class PrintMpaaCommand implements Command {
    public static String info = "print_mpaa command:\n" +
            "   This command will print to console collection in order of mpaa rating\n" +
            "   note: NC_17 > R > PG_13 > PG \n";

    /**
     * Creates a new PrintMpaaCommand object.
     */
    public PrintMpaaCommand() {
    }

    /**
     * Prints the collection to console in order of mpaa rating.
     * NC_17 > R > PG_13 > PG
     */
    @Override
    public void execute() {
        Comparator<Movie> mpaaComparator = Comparator.comparingInt(movie -> movie.getMpaaRating().ordinal());
        List<Movie> sortedMovies = new ArrayList<>(collectionManager.getMovies());
        sortedMovies.sort(mpaaComparator);

        for (Movie movie : sortedMovies) {
            System.out.println("$ " + movie);
        }
    }
}