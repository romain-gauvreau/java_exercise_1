import java.util.Scanner;

public class Launcher {
    public  static int fibo(int n)
    {
        if (n <= 1)
            return n;
        return fibo(n - 1) + fibo(n - 2);
    }

    public static void main(String[] args) {
        Scanner myObj = new Scanner(System.in);
        System.out.println("Enter command:");
        String inputCommand = myObj.nextLine();

        String quitCommand = "quit";

        while(!inputCommand.equals(quitCommand))
        {
           if(inputCommand.equals("fibo"))
           {
               System.out.println("Enter number:");
               int number = myObj.nextInt();
               myObj.nextLine();
               int result = fibo(number);
               System.out.println(result);
           }
           else {
               System.out.println("Unknown command");
           }
            inputCommand = myObj.nextLine();
        }
    }
}