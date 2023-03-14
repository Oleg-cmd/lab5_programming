package helpers;

import collectionWorker.Command;

import java.io.IOException;
/**
 A helper class for renaming files.
 */
public class FileNaming implements Command {
    public static String[] FileName(String[] fileNames){
        String[] newFileNames = new String[fileNames.length];
//        System.out.println(Arrays.toString(newFileNames));
        if(fileNames.length != 0){
            try{
                for(int i=0; i < fileNames.length; i++){
                    System.out.println("Please, enter the name of file with extention: " + fileNames[i]);
                    newFileNames[i] = reader.readLine().trim();
                }

            }catch (IOException e){
                System.out.println(e);
            }
        }
        return newFileNames;
    }

    /**
     * Executes the command to rename the files.
     */
    public void execute() {
        // TODO: Implement file renaming
    }
}
