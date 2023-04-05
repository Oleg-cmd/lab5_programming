package modules;

import helpers.CommandObject;

import java.io.*;
import java.net.Socket;

public class ClientConnection implements Cliented{
    public static void ClientConnet() throws IOException {
        Socket socket = new Socket("localhost", 1234);
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

// Create a new thread to continuously read from the server and analyze the input
        Thread inputThread = new Thread(() -> {
            String serverResponse;
            try {
                while ((serverResponse = in.readLine()) != null) {
                    InputAnalyzer.Analyze(serverResponse);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

// Create a new thread to continuously read from the user and send to the server
        Thread outputThread = new Thread(() -> {
            try {
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
                BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
                String userInput;
                while ((userInput = stdIn.readLine()) != null) {
                    CommandObject toSend = OutputAnalyzer.Analyze(userInput);
                    if (toSend != null) {
                        objectOutputStream.writeObject(toSend);
                        objectOutputStream.flush();
                    } else {
                        System.out.println("error in request body");
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

// Start the threads
        inputThread.start();
        outputThread.start();

// Wait for the threads to finish
        try {
            inputThread.join();
            outputThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

//        Cliented.print( "in, out is working correctly");

        // Clean up
        in.close();
        out.close();
        socket.close();
    }
}
