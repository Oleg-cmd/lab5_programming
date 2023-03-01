package collectionWorker;

import fileManager.Command;
import helpers.UserInputHandler;
import model.Movie;
import model.MpaaRating;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

/**
 * Command to add a new movie to the collection only if the movie's MPAA rating is greater than or equal to the
 * highest rated movie in the collection.
 */
public class AddIfMaxCommand implements Command {

    /**
     * A description of this command.
     */
    public static String info = "add_max command:\n" +
            "   This command will add new Movie if Rating of Movie that u want to enter is higher or equals some movies in collection\n" +
            "   Note: uses classic Add if it's okey with handler";

    private final HashSet<Movie> myCollection;
    private final UserInputHandler userInputHandler;

    /**
     * Constructs an AddIfMaxCommand with the given collection and user input handler.
     *
     * @param myCollection the collection to add the movie to
     * @param userInputHandler the user input handler
     */
    public AddIfMaxCommand(HashSet<Movie> myCollection, UserInputHandler userInputHandler) {
        this.myCollection = myCollection;
        this.userInputHandler = userInputHandler;
    }

    /**
     * Executes the command.
     *
     * The method prompts the user to enter the MPAA rating of the new movie to be added. If the rating is greater than
     * or equal to the highest rated movie in the collection, the user is prompted to enter the details of the new movie.
     * Otherwise, a message is printed to the console indicating that the rating is too low.
     */
    @Override
    public void execute() {
        try {
            // Sort the movies in the collection by MPAA rating
            Comparator<Movie> mpaaComparator = new Comparator<Movie>() {
                @Override
                public int compare(Movie movie1, Movie movie2) {
                    return Integer.compare(movie1.getMpaaRating().ordinal(), movie2.getMpaaRating().ordinal());
                }
            };
            List<Movie> sortedMovies = new ArrayList<>(myCollection);
            sortedMovies.sort(mpaaComparator);

            // Get the highest rated movie in the collection
            MpaaRating most = sortedMovies.get(0).getMpaaRating();

            // Prompt the user to enter the MPAA rating of the new movie
            System.out.print("Enter movie MPAA rating to add: ");
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            MpaaRating movieRating = MpaaRating.valueOf(reader.readLine().trim());

            // If the new movie's rating is greater than or equal to the highest rated movie in the collection, prompt the user to add the new movie
            if (Integer.compare(most.ordinal(), movieRating.ordinal()) >= 0) {
                System.out.println("OK, you can add the new movie.");
                userInputHandler.toExecute("add");
            } else {
                System.out.println("I'm sorry, but your rating is too low.");
            }
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}