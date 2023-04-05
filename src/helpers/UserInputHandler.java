package helpers;

import collectionWorker.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;


/**
 A class for handling user input and executing the appropriate command. It uses a HashMap to store all available commands and their corresponding Command objects,
 and it provides a start() method to continuously read and execute user input until the program is terminated. It also provides a toExecute() method for executing
 a single command outside the continuous loop.
 */
public class UserInputHandler implements Command {
    private static String historyPath = null;
    private static String execute = null;
    private static HashMap<String, Command> commands;

    /**
     * Constructs a UserInputHandler with a BufferedReader, a BufferedWriter, and a CollectionManager, and initializes the HashMap with all available commands.
     *
     */
    public UserInputHandler(String history, HashMap<String, Command> commands, String execute) {
        historyPath = history;
        UserInputHandler.execute = execute;
        UserInputHandler.commands = commands;
    }

    /**
     * Starts the continuous loop of reading and executing user input until the program is terminated.
     */

    public void start() {

        try {
            while (true) {
                String input = reader.readLine();
                String[] tokens = input.trim().split("\\s+");
                if (!input.equals("")) {
                    writer.write("$ ");
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
                        argCommand.run();
                    }

                    if (command == null) {
                        writer.write("Unknown command: " + commandName);
                        writer.newLine();
                    } else if (tokens.length == 1) {
                        SaveToHistoryCommand history = new SaveToHistoryCommand(commandName, historyPath);
                        history.execute();
                        command.execute();
                    }
                    writer.flush();
                }else{
                    System.out.print("$ ");
                }
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
    public static void toExecute(String instruction) throws IOException {
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
            argCommand.run();
        }

        if (command == null) {
            writer.write("Unknown command: " + commandName);
            writer.newLine();
        } else if (tokens.length == 1) {
            SaveToHistoryCommand history = new SaveToHistoryCommand(commandName, historyPath);
            history.execute();
            command.execute();
        }

        writer.flush();
    }
    public void execute() {
    }
}
