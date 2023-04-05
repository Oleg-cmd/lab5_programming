package helpers;

import collectionWorker.*;

import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;

/**
 A class for handling user input and executing the appropriate command. It uses a HashMap to store all available commands and their corresponding Command objects,
 and it provides a start() method to continuously read and execute user input until the program is terminated. It also provides a toExecute() method for executing
 a single command outside the continuous loop.
 */
public class InputHandler implements Command {
    private static String historyPath = null;
    private static String execute = null;
    private static HashMap<String, Command> commands;

    /**
     * Constructs a UserInputHandler with a BufferedReader, a BufferedWriter, and a CollectionManager, and initializes the HashMap with all available commands.
     *
     */
    public InputHandler(String history, HashMap<String, Command> commands, String execute) {
        historyPath = history;
        InputHandler.execute = execute;
        InputHandler.commands = commands;
    }

    public static CommandObject createCommandObject(Command command, Serializable argCommand, String commandName, String[] tokens) {
        return new CommandObject(command, argCommand, commandName, tokens);
    }

    /**
     * Executes a single command specified by the input string.
     * @param instruction the string that specifies the command to be executed
     * @throws IOException if there is an error reading or writing to the file
     */
    public static CommandObject toExecute(String instruction) throws IOException {
        String[] tokens = instruction.trim().split("\\s+");
        String commandName = tokens[0];
        Command command = commands.get(commandName);

        if (tokens.length > 1) {
            HashMap<String, Runnable> commands = new HashMap<>();
            commands.put("add_xml", () -> AddXmlCommand.AddingXML(tokens[1]));
            commands.put("update", () -> {
                if (tokens.length == 4) {
                    UpdateCommand.UpdatingArgs(tokens[1], tokens[2], tokens[3]);
                } else {
                    System.out.println("not enough args");
                }
            });
            commands.put("add_max", () -> AddIfMaxCommand.AddMaxArg(tokens[1], tokens[2]));
            commands.put("filter_by_name", () -> FilterByNameCommand.FilterByArg(tokens[1]));
            commands.put("remove_by_id", () -> RemoveByIdCommand.RemoveByArg(tokens[1]));
            commands.put("remove_lower", () -> RemoveLowerCommand.RemoveArg(tokens[1]));
            commands.put("execute_script", () -> {
                Command.updatePathCount(tokens[1], Command.pathCountMap);
                if(Command.pathCountMap.get(tokens[1]) < 2){
                    new ExecuteCommand(tokens[1]).execute();
                }
            });

            String argCommandName = tokens[0];
            Runnable argCommand = commands.get(argCommandName);
            SerializableRunnable serializableCommand = new SerializableRunnable(argCommand);

            return createCommandObject(null, serializableCommand, commandName, tokens);
        }



        if (command == null) {
            writer.write("Unknown command: " + commandName);
            writer.newLine();

        } else if (tokens.length == 1) {
//            new SaveToHistoryCommand(commandName, historyPath).execute();
            System.out.println("right command -> transfer to server");
            return createCommandObject(command, null, commandName, tokens);
        }
        writer.flush();

        return null;
    }
    public void execute() {
    }
}
