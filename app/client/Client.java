import modules.ClientConnection;


import java.io.*;

public class Client {

   public static void run(){
      try{
         ClientConnection.ClientConnet();
      }catch (IOException e){
         System.out.println(e);
      }
   }


}