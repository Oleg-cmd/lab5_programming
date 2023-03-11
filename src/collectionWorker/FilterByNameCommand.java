package collectionWorker;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

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

    /**
     * Constructs a new FilterByNameCommand with the specified movie collection.
     *
     */
    public FilterByNameCommand() {

    }

    public static void FilterByArg(String name){
        try{
            writer.write("Enter movie name to filter by: ");
            writer.flush();

            if (name.isEmpty()) {
                writer.write("Movie name cannot be empty.");
                return;
            }

            boolean foundMatch = false;
            for (Movie movie : collectionManager.getMovies()) {
                if (movie.getName().equals(name)) {
                    writer.write(movie.toString());
                    foundMatch = true;
                }
            }

            if (!foundMatch) {
                writer.write("No movies found with name " + name);
            }
        }catch (IOException e){
            System.out.println(e);
        }
    }
    /**
     *
     * Executes the FilterByNameCommand. Prompts the user to enter a movie name to search for, and then searches the
     * movie collection for any movies with that name. If any matches are found, it prints their details to the console.
     */

    @Override
    public void execute(){
        try{
        writer.write("Enter movie name to filter by: ");
        writer.flush();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String movieName = reader.readLine().trim();

        if (movieName.isEmpty()) {
            writer.write("Movie name cannot be empty.");
            return;
        }

        boolean foundMatch = false;
        for (Movie movie : collectionManager.getMovies()) {
            if (movie.getName().equals(movieName)) {
                writer.write(movie.toString());
                foundMatch = true;
            }
        }

        if (!foundMatch) {
            writer.write("No movies found with name " + movieName);
        }
        }catch (IOException e){
            System.out.println(e);
        }
    }
}