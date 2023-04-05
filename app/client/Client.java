import collectionWorker.*;
import helpers.InputHandler;
import helpers.MainHelper;
import modules.ClientConnection;


import java.io.*;
import java.util.HashMap;

public class Client {

   public static void run(){

      String[] path = new MainHelper().MainHelper();

      String history = path[0];
      String execute = path[1];
      String collection = path[2];

      HashMap<String, Command> commands = new HashMap<>();

      commands.put("show", new ShowCommand());
      commands.put("update", new UpdateCommand());
      commands.put("add", new AddCommand());
      commands.put("add_xml", new AddXmlCommand());
      commands.put("clear", new ClearCommand());
      commands.put("help", new HelpCommand(commands));
      commands.put("exit", new ExitCommand(history));
      commands.put("info", new InfoCommand());
      commands.put("remove_lower", new RemoveLowerCommand());
      commands.put("print_mpaa", new PrintMpaaCommand());
      commands.put("history", new ShowHistoryCommand(history));
      commands.put("add_max", new AddIfMaxCommand());
      commands.put("execute_script", new ExecuteCommand(execute));
      commands.put("remove_by_id", new RemoveByIdCommand());
      commands.put("save", new SaveCommand(collection));
      commands.put("filter_by_name", new FilterByNameCommand());
      commands.put("clear_history", new ClearHistoryCommand(history));

      InputHandler inputHandler = new InputHandler(history, commands, execute);
      try{
         ClientConnection.ClientConnet();
      }catch (IOException e){
         System.out.println(e);
      }

   }



}