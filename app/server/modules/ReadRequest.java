package modules;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ReadRequest {
    public static void Processing(Socket clientSocket) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
        String inputLine;
        while ((inputLine = in.readLine()) != null) {
            // Process the input from the client
            String outputLine = ProcessRequest.Process(inputLine);
            System.out.println(outputLine);
            // Send the output back to the client
            out.println("Server recieved: " +  outputLine);

            if (outputLine.equals("exit")) {
                break;
            }
        }
    }
}
