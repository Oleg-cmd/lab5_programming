import collectionWorker.*;
import helpers.MainHelper;
import helpers.UserInputHandler;

import java.util.HashMap;
import java.util.Map;

/**
 The main class for the application.
 */
public class Main {

    /**
     * The main method of the application. Initializes the command objects, collection manager, file reader and writer,
     * and starts the user input handling process.
     *
     * @param args command line arguments
     */


    public static void main(String[] args) {

        String[] path = new MainHelper().MainHelper();

        String history = path[0];
        String execute = path[1];
        String collection = path[2];

        HashMap<String, Command> commands = new HashMap<>();

        commands.put("show", new ShowCommand());
        commands.put("update", new UpdateCommand());
        commands.put("add", new AddCommand());
        commands.put("add_xml", new AddXmlCommand());
        commands.put("clear", new ClearCommand());
        commands.put("help", new HelpCommand(commands));
        commands.put("exit", new ExitCommand(history));
        commands.put("info", new InfoCommand());
        commands.put("remove_lower", new RemoveLowerCommand());
        commands.put("print_mpaa", new PrintMpaaCommand());
        commands.put("history", new ShowHistoryCommand(history));
        commands.put("add_max", new AddIfMaxCommand());
        commands.put("execute_script", new ExecuteCommand(execute));
        commands.put("remove_by_id", new RemoveByIdCommand());
        commands.put("save", new SaveCommand(collection));
        commands.put("filter_by_name", new FilterByNameCommand());
        commands.put("clear_history", new ClearHistoryCommand(history));

        UserInputHandler userInputHandler = new UserInputHandler(history, commands, execute);
        new ClearHistoryCommand(history).execute();


        userInputHandler.start();
    }

}