package collectionWorker;

import model.Movie;

import java.io.IOException;
import java.util.HashSet;

/**
 The ShowCommand class implements the Command interface and represents the "show" command.
 This command is used to display all the movies in the collection in a string format.
 */

public class ShowCommand implements Command {
    public static String info = "show command:\n" +
            "   This command will show u all collection in string format\n";

    /**
     * Executes the "show" command by getting all the movies in the collection and displaying them in a string format.
     */

    @Override
    public void execute() {
        HashSet<Movie> movies = collectionManager.getMovies();

        if (movies.isEmpty()) {
            try {
                writer.write("The collection is empty.");
                return;
            }catch (IOException e){
                System.out.println(e);
            }

        }
        StringBuilder sb = new StringBuilder();
        for (Movie movie : movies) {
            sb.append(movie.toString());
        }
        System.out.println(sb);
    }
}