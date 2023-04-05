package helpers;

import collectionWorker.Command;

import java.io.Serializable;

public record CommandObject(Command command, Serializable argCommand, String commandName, String[] tokens) implements Serializable {

    public Command getCommand() {
        return command;
    }

    public Serializable getArgCommand() {
        return argCommand;
    }

    public String getCommandName() {
        return commandName;
    }

    public String[] getTokens() {
        return tokens;
    }

}
