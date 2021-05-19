import java.util.Scanner;

public interface Command {
    String name();
    Boolean run(Scanner scanner);
}
