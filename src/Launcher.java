import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Launcher {
    public static int fibo(int n) {
        if (n <= 1)
            return n;
        return fibo(n - 1) + fibo(n - 2);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean shouldQuit = false;
        do {
            System.out.println("Please, input a command:");
            String command = scanner.nextLine();
            if ("quit".equals(command)) {
                shouldQuit = true;
                break;
            } else if ("fibo".equals(command)) {
                System.out.println("Input a number:");
                int n = scanner.nextInt();
                scanner.nextLine();
                System.out.println(fibo(n));
            } else if ("freq".equals(command)) {
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
            } else {
                System.out.println("Unknown command");
            }
        } while (!shouldQuit);

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
}