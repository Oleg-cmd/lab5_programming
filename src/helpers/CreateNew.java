package helpers;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class CreateNew {
    public static void Create(){
        try {
            // create empty file "history" in parent directory
            File historyFile = new File("../history");
            historyFile.createNewFile();

            // create empty file "script" in parent directory
            File scriptFile = new File("../script");
            scriptFile.createNewFile();

            // create file "collection.xml" in parent directory with specified content
            String xmlContent = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>\n" +
                    "<root>\n" +
                    "</root>";
            File xmlFile = new File("../collection.xml");
            xmlFile.createNewFile();
            FileWriter writer = new FileWriter(xmlFile);
            writer.write(xmlContent);
            writer.close();

            System.out.println("Files created successfully.");
        } catch (IOException e) {
            System.out.println("An error occurred while creating files.");
            e.printStackTrace();
        }
    }
}
