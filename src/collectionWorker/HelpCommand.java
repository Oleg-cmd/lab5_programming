package collectionWorker;

import fileManager.Command;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.HashMap;

/**
 HelpCommand is a class that implements the Command interface.
 This command will give all instructions of usage any function in this app.
 */

public class HelpCommand implements Command {
    public static String info = "help command\n" +
            "   This command will give u all instructions of usage any function in this app\n";

    static String[] instructions = new String[]{AddCommand.info, AddIfMaxCommand.info, ClearCommand.info, ClearHistoryCommand.info, ExecuteCommand.info, ExitCommand.info, FilterByNameCommand.info, info, InfoCommand.info, PrintMpaaCommand.info, RemoveByIdCommand.info, RemoveLowerCommand.info, SaveCommand.info, ShowCommand.info, ShowHistoryCommand.info, UpdateCommand.info};

    private final HashMap<String, Command> commands;
    private final BufferedWriter writer;
    private final BufferedReader reader;


    /**
     * Constructs a HelpCommand with a HashMap of commands, a BufferedWriter and a BufferedReader.
     *
     * @param commands the HashMap of commands to display
     * @param writer the BufferedWriter to display the output
     * @param reader the BufferedReader to read the user input
     */

    public HelpCommand(HashMap<String, Command> commands, BufferedWriter writer, BufferedReader reader) {
        this.commands = commands;
        this.writer = writer;
        this.reader = reader;
    }


    /**
     * Executes the command.
     * Displays a list of available commands.
     * If the user types 'more', additional information is displayed for each command.
     */

    @Override
    public void execute() {
        try {
            writer.write("List of available commands:\n");
            for (String commandName : commands.keySet()) {
                writer.write(commandName);
                writer.newLine();
            }
            writer.flush();
            System.out.println("Type %more% if u want to see additional information");
            String text = reader.readLine().trim();
            if(text.equals("more")){
                for(String s : instructions){
                    System.out.println(s);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
