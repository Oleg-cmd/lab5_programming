package collectionWorker;

/**
 This class represents the command to exit the program.
 */

public class ExitCommand implements Command {
    public static String info = "exit command:\n" +
            "   This command will exit from program (no-autosave)\n";
    private final String history;
    /**
     * Executes the command to exit the program.
     * Clears the history and terminates the JVM with status code 0.
     */

    public ExitCommand(String history) {
        this.history = history;
    }
    @Override
    public void execute() {
        ClearHistoryCommand clear = new ClearHistoryCommand(history);
        System.exit(0);
    }
}
