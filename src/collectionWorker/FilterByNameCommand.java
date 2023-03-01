package collectionWorker;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;

import fileManager.Command;
import model.Movie;

/**
 FilterByNameCommand is a class that implements the Command interface and is responsible for filtering the movies in
 the collection by name. It prompts the user to enter a movie name, and then searches through the collection for any
 movies with that name. If any matches are found, it prints their details to the console.
 The syntax for using this command is: "filter_by_name nameThatIWantToFind"
 */
public class FilterByNameCommand implements Command {
    public static String info = "filter_by_name command:\n" +
            "   This command will find all models, that name is the same that u entered\n" +
            "   Syntax:\n" +
            "       filter_by_name nameThatIWantToFind\n";
    private HashSet<Movie> movieCollection;

    /**
     * Constructs a new FilterByNameCommand with the specified movie collection.
     *
     * @param movieCollection the HashSet of movies to filter
     */
    public FilterByNameCommand(HashSet<Movie> movieCollection) {
        this.movieCollection = movieCollection;
    }

    /**
     * Executes the FilterByNameCommand. Prompts the user to enter a movie name to search for, and then searches the
     * movie collection for any movies with that name. If any matches are found, it prints their details to the console.
     */

    @Override
    public void execute(){
        try{
        System.out.print("Enter movie name to filter by: ");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String movieName = reader.readLine().trim();

        if (movieName.isEmpty()) {
            System.out.println("Movie name cannot be empty.");
            return;
        }

        boolean foundMatch = false;
        for (Movie movie : movieCollection) {
            if (movie.getName().equals(movieName)) {
                System.out.println(movie.toString());
                foundMatch = true;
            }
        }

        if (!foundMatch) {
            System.out.println("No movies found with name " + movieName);
        }
        }catch (IOException e){
            System.out.println(e);
        }
    }
}