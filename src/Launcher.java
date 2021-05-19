import java.util.*;

public class Launcher {
    public static void main(String[] args) {
        List<Command> commands = List.of(new Quit(), new Fibo(), new Freq());

        Scanner scanner = new Scanner(System.in);
        boolean shouldQuit = false;

        do {
            System.out.println("Please, input a command:");
            String userInput = scanner.nextLine();
            Optional<Command> potentialCommand = commands.stream()
                    .filter(c -> c.name().equals(userInput))
                    .findFirst();
            if (potentialCommand.isEmpty()) {
                System.out.println("Command unknown");
            } else {
                shouldQuit = potentialCommand.get().run(scanner);
            }
        } while (!shouldQuit);
    }
}