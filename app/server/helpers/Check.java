package helpers;

import collectionWorker.HelpCommand;

public class Check {
    public static boolean CheckName(String name){
        System.out.println(name);
        String[] instructions = HelpCommand.myFuncsName;
        for(String n: instructions){
            if (n.equals(name)){
                return true;
            }

        }
        return false;
    }
}
