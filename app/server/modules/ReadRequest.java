package modules;

import helpers.CommandObject;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.net.Socket;

public class ReadRequest {
    public static void Reading(Socket clientSocket) {
        try (ObjectInputStream in = new ObjectInputStream(clientSocket.getInputStream());
             PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true)) {
            Object inputObject;
            while ((inputObject = in.readObject()) != null) {
                // Process the input from the client
                CommandObject inputCommand = (CommandObject) inputObject;
                String outputLine = ProcessRequest.Process(inputCommand);

                System.out.println(outputLine);
                // Send the output back to the client
                out.println(outputLine);
                if (inputCommand.getCommandName().equals("exit")) {
                    break;
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
