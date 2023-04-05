package modules;

import helpers.Check;
import helpers.CommandObject;


public class ProcessRequest {
    public static String Process(CommandObject inputCommand) {
        if(Check.CheckName(inputCommand.getCommandName())){
            return "correct command"+ " -- server";
        }else {
            return "non correct"+ " -- server";
        }
//        return inputCommand.getCommandName() + Arrays.toString(inputCommand.getTokens()) + inputCommand.toString() + " -- server";
    }
}