import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Predict implements Command {

    @Override
    public String name() {
        return "predict";
    }

    @Override
    public Boolean run(Scanner scanner) {
        try {
            System.out.println("Input a file path:");
            String path = scanner.nextLine();
            Path filePath = Paths.get(path);
            String content = Files.readString(filePath);

            String[] words = content.split(" ");
            Stream<String> wordStream = Arrays.stream(words).filter(s -> !s.isBlank());
            words = wordStream.toArray(String[]::new);

            Set<String> uniqueWords = Arrays.stream(words).collect(Collectors.toSet());
            Map<String, String> nextWords = new HashMap<>();

            for (String word : uniqueWords) {
                Map<String, Long> frequency = new HashMap<>();
                for (int i = 0; i < words.length; ++i) {
                    if (words[i].equals(word) && i + 1 < words.length) {
                        frequency.putIfAbsent(words[i + 1], 1L);
                        frequency.computeIfPresent(words[i + 1], (key, val) -> val + 1);
                    }
                }

                nextWords.put(word, frequency.entrySet().stream()
                        .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
                        .limit(1).map(Map.Entry::getKey)
                        .collect(Collectors.joining(""))
                );
            }

            System.out.println("Input a word:");
            String user_word = scanner.nextLine();

            if (uniqueWords.contains(user_word)) {
                String previous = user_word;
                for (int i = 0; i < 20; ++i) {
                    String word = nextWords.get(previous);
                    if (word != null && !word.equals("")) {
                        System.out.print(word + " ");
                        previous = word;
                    } else {
                        break;
                    }
                }
                System.out.println();
            } else {
                System.out.println("The file does not contain this word.");
            }
        } catch (IOException e) {
            System.err.println("Unreadable file: " + e);
        }
        return false;
    }
}
