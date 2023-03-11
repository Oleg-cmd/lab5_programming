import collectionWorker.*;
import helpers.FileNaming;
import helpers.FindFiles;
import helpers.UserInputHandler;

import java.io.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 The main class for the application.
 */
public class Main {

    /**
     * The main method of the application. Initializes the command objects, collection manager, file reader and writer,
     * and starts the user input handling process.
     *
     * @param args command line arguments
     */
    public static void main(String[] args) {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        String history = null;
        String execute = null;
        String collection = null;

        try{
            while (true){
                writer.write("If u want to use CUSTOM name of files (default: history, script, collection.xml) type y, else type n: ");
                writer.flush();
                String[] fileNames = new String[] { "history", "script", "collection.xml" }; // Modify as needed
                String[] customNames;
                boolean breakPoint = false;

                if(reader.readLine().equals("n")){
                    Map<String, String> filesPath = FindFiles.FindFiles(fileNames);
                    if(filesPath == null){
                        System.out.println("Wrong data");
                    }else {
                        history = filesPath.get(fileNames[0]);
                        execute = filesPath.get(fileNames[1]);
                        collection = filesPath.get(fileNames[2]);
                        break;
                    }
                }else{
                    customNames = FileNaming.FileName(fileNames);
                    System.out.println(Arrays.toString(customNames));
                    for(String name : customNames){
                        if(name.equals("") || name.equals(" ")){
                         breakPoint = true;
                        }
                    }
                    if(breakPoint){
                        System.out.println("Wrong data");
                    }else{
                        Map<String, String> filesPath = FindFiles.FindFiles(customNames);
                        if(filesPath == null){
                            System.out.println("Wrong data");
                        }else {
                            history = filesPath.get(fileNames[0]);
                            execute = filesPath.get(fileNames[1]);
                            collection = filesPath.get(fileNames[2]);
                            break;
                        }
                    }
                }
            }

            ParseXmlCommand parseXmlCommand = new ParseXmlCommand(collection);
            parseXmlCommand.execute();

            writer.write("Collection loaded from file: " + collection);
            writer.newLine();
            writer.flush();

        }catch (IOException e){
            System.out.println(e);
        }

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

        UserInputHandler userInputHandler = new UserInputHandler(history, commands);
        new ClearHistoryCommand(history).execute();
        userInputHandler.start();
    }

}