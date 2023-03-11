package collectionWorker;

import helpers.MethodReturn;
import helpers.Worker;
import model.Movie;

import java.io.IOException;
import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.function.Consumer;


/**
 * A command that adds a new movie to the collection.
 */
public class AddCommand implements Command {

    /**
     * A string containing information about how to use this command.
     */

    public static String info = "add command:\n" +
            "   This command can add elem\n" +
            "   Syntax:\n" +
            "       add\n" +
            "   then program will require for u to input to console every field of this class\n";

    public void Adding(){
        MethodReturn custom = Worker.Code(null);
        HashMap<String, Consumer<String>> setters = custom.getSetters();
        try {
            for (String field : setters.keySet()) {
                String value;
                while (true){
                    System.out.print(field + ": ");
                    value = reader.readLine().trim();
                    if (value.isEmpty()) {
                        System.out.println("Field cannot be empty");
                    }else {
                        break;
                    }
                }
                setters.get(field).accept(value);
            }
        }catch (IOException e){
            System.out.println(e);
        }
        Movie movie = custom.getMovie();

        if (collectionManager.getMovies().contains(movie)) {
            throw new IllegalArgumentException("Movie with same parameters already exists in the collection");
        }

        movie.setId(collectionManager.getRandomID());
        movie.setCreationDate(ZonedDateTime.now());
        collectionManager.getMovies().add(movie);

        System.out.println("Movie added to collection with ID " + movie.getId());
    }

    @Override
    public void execute() {
        Adding();
        System.out.println("Movie was successfully added");
    }



}
