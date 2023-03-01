package collectionWorker;

import fileManager.Command;
import model.Movie;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

/**
 This class implements the Command interface to print the collection to console in order of mpaa rating.
 NC_17 > R > PG_13 > PG
 */
public class PrintMpaaCommand implements Command {
    public static String info = "print_mpaa command:\n" +
            "   This command will print to console collection in order of mpaa rating\n" +
            "   note: NC_17 > R > PG_13 > PG \n";
    private final HashSet<Movie> myCollection;

    /**
     * Creates a new PrintMpaaCommand object.
     * @param myCollection The collection of movies to be printed.
     */
    public PrintMpaaCommand(HashSet<Movie> myCollection) {
        this.myCollection = myCollection;
    }

    /**
     * Prints the collection to console in order of mpaa rating.
     * NC_17 > R > PG_13 > PG
     */
    @Override
    public void execute() {
        Comparator<Movie> mpaaComparator = new Comparator<Movie>() {
            @Override
            public int compare(Movie movie1, Movie movie2) {
                return Integer.compare(movie1.getMpaaRating().ordinal(), movie2.getMpaaRating().ordinal());
            }
        };

        List<Movie> sortedMovies = new ArrayList<>(myCollection);
        sortedMovies.sort(mpaaComparator);

        for (Movie movie : sortedMovies) {
            System.out.println(movie);
        }
    }
}