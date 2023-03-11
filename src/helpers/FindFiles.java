package helpers;

import java.io.File;
import java.util.LinkedHashMap;
import java.util.Map;

public class FindFiles {
public static String findFile(String fileName) {
    // Check in parent directory
    String filePath = "../" + fileName;
    File file = new File(filePath);
    if (file.exists()) {
        return file.getPath();
    }

    // Check in project directory
    filePath = "./" + fileName;
    file = new File(filePath);
    if (file.exists()) {
        return file.getPath();
    }

    // Check in subdirectories of project directory
    String[] directories = new String[] { "src", "lib", "bin" }; // Modify as needed
    for (String directory : directories) {
        filePath = "./" + directory + "/" + fileName;
        file = new File(filePath);
        if (file.exists()) {
            return file.getPath();
        } else {
            File[] nestedFiles = file.listFiles();
            if (nestedFiles != null) {
                for (File nestedFile : nestedFiles) {
                    if (nestedFile.getName().equals(fileName)) {
                        return nestedFile.getPath();
                    }
                }
            }
        }
    }

    // File not found
    return null;
}

    public static Map<String, String> FindFiles(String[] fileNames){
        Map<String, String> filesMap = new LinkedHashMap<>();
        for (String fileName : fileNames) {
            String filePath = findFile(fileName);
            if (filePath != null) {
//                System.out.println("Found " + fileName + " at " + filePath);
                filesMap.put(fileName, filePath); // add to the files map
            } else {
                System.out.println("Could not find " + fileName);
                return null;
            }
        }
//        System.out.println("Files Map: " + filesMap); // print the files map
        return filesMap;
    }

}
