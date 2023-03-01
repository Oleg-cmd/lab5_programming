package collectionWorker;

import fileManager.Command;
import helpers.UserInputHandler;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 A class that represents the execute_script command, which executes all the instructions from a given file path.
 */
public class ExecuteCommand implements Command {
    public static String info = "execute_script command:\n" +
            "   This command will execute all instructions from script\n" +
            "   Note: script should be without errors\n";
    private final String filePath;
    private final UserInputHandler userInputHandler;

    /**
     * Constructor for ExecuteCommand class.
     * @param filePath the path of the file to be read
     * @param userInputHandler UserInputHandler object
     */

    public ExecuteCommand(String filePath, UserInputHandler userInputHandler) {
        this.filePath = filePath;
        this.userInputHandler = userInputHandler;
    }


    /**
     * Reads the commands from the file and executes them.
     */

    @Override
    public void execute(){
        try{
            List<String> commands = readCommandsFromFile(filePath);
            for (String command : commands) {
                System.out.println(command);
                userInputHandler.toExecute(command);
            }
        } catch (IOException e){
            System.out.println(e);
        }

    }

    /**
     * Reads the commands from the file.
     * @param filePath the path of the file to be read
     * @return List of commands from file
     * @throws IOException if an I/O error occurs
     */

    private List<String> readCommandsFromFile(String filePath) throws IOException {
        List<String> commands = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                commands.add(line);
            }
        }
        return commands;
    }
}