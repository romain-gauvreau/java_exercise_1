import java.util.Scanner;

public class Launcher {
    public static void main(String[] args) {
        Scanner myObj = new Scanner(System.in);
        System.out.println("Enter command:");
        String inputCommand = myObj.nextLine();

        String quitCommand = "quit";

        if (!inputCommand.equals(quitCommand))
        {
            System.out.println("Unknown command");

        }
    }
}