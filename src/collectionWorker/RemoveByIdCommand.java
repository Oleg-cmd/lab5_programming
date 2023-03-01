package collectionWorker;

import fileManager.CollectionManager;
import fileManager.Command;
import model.Movie;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

/**
 This class represents a command to remove a movie from a collection by its ID.
 */
public class RemoveByIdCommand implements Command {
    public static String info = "remove_by_id command:\n" +
            "   This command will delete model with id that u entered (if this model exist)\n";
    private final CollectionManager collectionManager;
    private final BufferedReader reader;
    private final BufferedWriter writer;

    /**
     * Creates a new instance of the command with the given collection manager, reader, and writer.
     *
     * @param collectionManager the collection manager to use
     * @param reader the reader to use for user input
     * @param writer the writer to use for output to the user
     */

    public RemoveByIdCommand(CollectionManager collectionManager, BufferedReader reader, BufferedWriter writer) {
        this.collectionManager = collectionManager;
        this.reader = reader;
        this.writer = writer;
    }

    /**
     * Executes the command.
     *
     * Asks the user to input an ID of a movie to remove, removes the movie from the collection,
     * and outputs a message indicating whether the movie was removed.
     */

    @Override
    public void execute() {
        try{
            writer.write("Enter movie ID to remove:");
            writer.newLine();
            writer.flush();

            String input = reader.readLine().trim();
            int id;
            try {
                id = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                writer.write("Invalid input. ID must be an integer.");
                writer.newLine();
                writer.flush();
                return;
            }

            Movie movie = collectionManager.getById(id);
            boolean removed = collectionManager.removeMovie(movie);
            if (removed) {
                writer.write("Movie with ID " + id + " has been removed.");
                writer.newLine();
            } else {
                writer.write("No movie found with ID " + id + ".");
                writer.newLine();
            }
            writer.flush();
        }catch (IOException e){
            System.out.println(e);
        }

    }
}