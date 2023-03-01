package collectionWorker;
import fileManager.Command;

/**
 This class represents the command to exit the program.
 */

public class ExitCommand implements Command {
    public static String info = "exit command:\n" +
            "   This command will exit from program (no-autosave)\n";

    /**
     * Executes the command to exit the program.
     * Clears the history and terminates the JVM with status code 0.
     */

    @Override
    public void execute() {
        ClearHistoryCommand clear = new ClearHistoryCommand();
        System.exit(0);
    }
}
