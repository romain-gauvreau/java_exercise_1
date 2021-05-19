import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

interface Command {
    String name();
    Boolean run(Scanner scanner);
}

class Quit implements Command {
    @Override
    public String name() {
        return "quit";
    }

    @Override
    public Boolean run(Scanner scanner) {
        return true;
    }
}

class Fibo implements Command {
    @Override
    public String name() {
        return "fibo";
    }

    private static int fibo(int n) {
        if (n <= 1)
            return n;
        return fibo(n - 1) + fibo(n - 2);
    }

    @Override
    public Boolean run(Scanner scanner) {
        System.out.println("Input a number:");
        int n = scanner.nextInt();
        scanner.nextLine();
        System.out.println(fibo(n));
        return false;
    }
}

class Freq implements Command {
    @Override
    public String name() {
        return "freq";
    }

    private static void displayThreeMostOccurringWords(String[] words) {
        Stream<String> wordStream = Arrays.stream(words);

        Map<String, Long> countsByWord = wordStream
                .filter(s -> !s.isBlank())
                .map(s -> s.toLowerCase())
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));


        Comparator<Map.Entry<String, Long>> countReversed =
                Comparator.<Map.Entry<String, Long>, Long>comparing(e -> e.getValue())
                        .reversed();

        String threeMostOccuringWords = countsByWord.entrySet().stream()
                .sorted(countReversed)
                .limit(3).map(e -> e.getKey())
                .collect(Collectors.joining(" "));

        System.out.println(threeMostOccuringWords);
    }

    @Override
    public Boolean run(Scanner scanner) {
        try {
            System.out.println("Input a file path:");
            String path = scanner.nextLine();
            Path filePath = Paths.get(path);
            String content = Files.readString(filePath);
            String[] words = content.split(" ");
            displayThreeMostOccurringWords(words);
        } catch (IOException e) {
            System.out.println("Unreadable file: " + e.getClass() + e.getMessage());
        }
        return false;
    }
}

public class Launcher {
    public static void main(String[] args) {
        List<Command> commandList = new ArrayList<Command>();
        commandList.add(new Freq());
        commandList.add(new Fibo());
        commandList.add(new Quit());

        Scanner scanner = new Scanner(System.in);
        boolean shouldQuit = false;
        boolean commandFound = false;

        do {
            System.out.println("Please, input a command:");
            String command = scanner.nextLine();
            for (Command current : commandList) {
                if (current.name().equals(command)) {
                    commandFound = true;
                    shouldQuit = current.run(scanner);
                    break;
                }
            }
            if (commandFound) {
                commandFound = false;
            } else {
                System.out.println("Command unknown");
            }
        } while (!shouldQuit);
    }
}