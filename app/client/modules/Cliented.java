package modules;

public interface Cliented {
    String helper = "CLIENT || ";
    static void print(String s){
        System.out.println(helper + s);
    }
}
