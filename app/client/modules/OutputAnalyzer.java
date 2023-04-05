package modules;

import helpers.CommandObject;
import helpers.InputHandler;

import java.io.IOException;

public class OutputAnalyzer implements Cliented {
    public static CommandObject Analyze(String output) throws IOException {
        // Perform analysis on the output
        CommandObject commandObject = InputHandler.toExecute(output);
        if(commandObject != null){
            return commandObject;
        }
        return null;
    }
}
