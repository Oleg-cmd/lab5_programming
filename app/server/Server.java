
import modules.Connection;
import modules.ReadRequest;

import java.net.*;
import java.io.*;

public class Server {
    public static void run(){
        try{
            Socket socket = Connection.Connect();
            ReadRequest.Processing(socket);
        }catch (IOException e){
            System.out.println(e);
        }

    }
}
