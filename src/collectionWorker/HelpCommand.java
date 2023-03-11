package collectionWorker;

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


    /**
     * Constructs a HelpCommand with a HashMap of commands, a BufferedWriter and a BufferedReader.
     *
     * @param commands the HashMap of commands to display
     */

    public HelpCommand(HashMap<String, Command> commands) {
        this.commands = commands;
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
            writer.write("Type %more% if u want to see additional information:");
            writer.newLine();
            writer.flush();

            String text = reader.readLine().trim();
            if(text.equals("more")){
                for(String s : instructions){
                    writer.write(s);
                }
            }
        } catch (IOException e) {
            System.out.println(e);
//            e.printStackTrace();
        }
    }
}
