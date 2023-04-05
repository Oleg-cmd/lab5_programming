package collectionWorker;


public class AddXmlCommand implements Command {
    public static String name = "add_xml";
    public static void AddingXML(String XMLPath){
//      just adding to collection some models, no checking of fields
        ParseXmlCommand parseXmlCommand = new ParseXmlCommand(XMLPath);
        parseXmlCommand.execute();
        System.out.println("Ur xml models was added successfully");
    }

    @Override
    public void execute() {
    }
}
