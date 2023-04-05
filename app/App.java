public class App {
    public static void main(String[] args){
        // for developers only
        new Thread(Client::run).start();
        new Thread(Server::run).start();
    }
}
