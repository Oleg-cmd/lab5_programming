package helpers;

import collectionWorker.ParseXmlCommand;

import java.io.*;
import java.util.Arrays;
import java.util.Map;

/**
 * A helper class for the main application.
 */
public class MainHelper {
    /**
     * Main method for the MainHelper class.
     * @return An array of Strings representing the file paths for the history, execute, and collection files.
     */
    public String[] MainHelper(){
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
                        if (name.equals("") || name.equals(" ")) {
                            breakPoint = true;
                            break;
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
        return new String[]{history, execute, collection};
    }
}
