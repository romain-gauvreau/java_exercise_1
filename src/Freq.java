import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Map;
import java.util.Scanner;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Freq implements Command {
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
