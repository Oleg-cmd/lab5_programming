package modules;

import java.net.*;
import java.io.*;


public class Connection {
    public static Socket Connect() throws IOException {
        ServerSocket serverSocket = new ServerSocket(1234);
        Socket clientSocket = serverSocket.accept();
        System.out.println("Client connected");

        return clientSocket;
    }
}
