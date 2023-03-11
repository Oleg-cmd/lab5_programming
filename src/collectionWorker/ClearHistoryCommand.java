package collectionWorker;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 A command that clears the history file.
 */
public class ClearHistoryCommand implements Command {
    public static String info = "clear_history command:\n" +
            "   This command is addictional, if u want to clear history manually and now, it will clear it\n";
    private String history = "";
    public ClearHistoryCommand(String history) {
        this.history = history;
    }

    /**
     * Clears the history file by writing an empty string to it.
     * If there is an error while clearing the file, an error message is printed to the console.
     */

    @Override
    public void execute() {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(history));
            writer.write("");
            writer.close();
            System.out.println("History cleared.");
            System.out.print("$ ");
        } catch (IOException e) {
            System.err.println("An error occurred while clearing the history file.");
            e.printStackTrace();
        }
    }
}
