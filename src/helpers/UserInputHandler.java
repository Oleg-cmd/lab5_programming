package helpers;

import collectionWorker.*;
import fileManager.CollectionManager;
import fileManager.Command;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.Random;

/**
 A class for handling user input and executing the appropriate command. It uses a HashMap to store all available commands and their corresponding Command objects,
 and it provides a start() method to continuously read and execute user input until the program is terminated. It also provides a toExecute() method for executing
 a single command outside the continuous loop.
 */
public class UserInputHandler {
    private final HashMap<String, Command> commands;
    private final BufferedReader reader;
    private final BufferedWriter writer;

    Random rand = new Random();
    int upperbound = 9999999;
    int int_random = rand.nextInt(upperbound);


    /**
     * Constructs a UserInputHandler with a BufferedReader, a BufferedWriter, and a CollectionManager, and initializes the HashMap with all available commands.
     *
     * @param reader the BufferedReader used to read user input
     * @param writer the BufferedWriter used to output program responses
     * @param collectionManager the CollectionManager object that stores the collection of movies
     */
    public UserInputHandler(BufferedReader reader, BufferedWriter writer, CollectionManager collectionManager) {
        this.reader = reader;
        this.writer = writer;

        commands = new HashMap<>();
        commands.put("show", new ShowCommand(collectionManager));
        commands.put("update", new UpdateCommand(collectionManager.getMovies(), reader, writer, collectionManager));
        commands.put("add", new AddCommand(collectionManager, reader, int_random, ZonedDateTime.now()));
        commands.put("clear", new ClearCommand(collectionManager));
        commands.put("help", new HelpCommand(commands, writer, reader));
        commands.put("exit", new ExitCommand());
        commands.put("info", new InfoCommand(collectionManager.getMovies()));
        commands.put("remove_lower", new RemoveLowerCommand(collectionManager.getMovies(), collectionManager));
        commands.put("print_mpaa", new PrintMpaaCommand(collectionManager.getMovies()));
        commands.put("history", new ShowHistoryCommand("./src/history"));
        commands.put("add_max", new AddIfMaxCommand(collectionManager.getMovies(), this));
        commands.put("execute_script", new ExecuteCommand("./src/script", this));
        commands.put("remove_by_id", new RemoveByIdCommand(collectionManager, reader, writer));
        commands.put("save", new SaveCommand("./src/collection.xml", collectionManager.getMovies()));
        commands.put("filter_by_name", new FilterByNameCommand(collectionManager.getMovies()));
        commands.put("clear_history", new ClearHistoryCommand());
    }

    /**
     * Starts the continuous loop of reading and executing user input until the program is terminated.
     */

    public void start() {
        try {
            while (true) {
                String input = reader.readLine();
                String[] tokens = input.trim().split("\\s+");
                if (tokens.length == 0) {
                    continue;
                }

                String commandName = tokens[0];
                Command command = commands.get(commandName);
                if (command == null) {
                    writer.write("Unknown command: " + commandName);
                    writer.newLine();
                } else {
                    SaveToHistoryCommand history = new SaveToHistoryCommand(commandName, "./src/history");
                    history.execute();
                    command.execute();
                }
                writer.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
/**
 * Executes a single command specified by the input string.
 * @param instruction the string that specifies the command to be executed
 * @throws IOException if there is an error reading or writing to the file
*/
    public void toExecute(String instruction) throws IOException {
        Command command = commands.get(instruction);
        if (command == null) {
            writer.write("Unknown command: " + instruction);
            writer.newLine();
        } else {
            command.execute();
        }
    }


}
