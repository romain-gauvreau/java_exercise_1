import java.util.Scanner;

public class Fibo implements Command {
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
