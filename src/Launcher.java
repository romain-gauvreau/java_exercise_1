import java.util.Scanner;

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
            } else {
                System.out.println("Unknown command");
            }
        } while (!shouldQuit);

    }
}