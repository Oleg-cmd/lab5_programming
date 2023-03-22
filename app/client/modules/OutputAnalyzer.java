package modules;

public class OutputAnalyzer implements Cliented {
    public static void Analyze(String output) {
        // Perform analysis on the output
        Cliented.print("Sent output: " + output);
    }
}
