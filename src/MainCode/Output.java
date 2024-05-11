package MainCode;
import java.util.Scanner;

public class Output {

    // Initialize scanner for user input
    public static final Scanner scanner = new Scanner(System.in);

        // Print text with delay for dramatic effect
        public static void slowPrint(String input) {
            for (int i = 0; i < input.length(); i++) {
                System.out.print(input.charAt(i));
                try {
                        Thread.sleep(30); // Faster printing in dungeons
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }

            // Clear the screen for better readability
    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }


    // Wait for a specified amount of time
    public static void wait(int time) {
        try {
                Thread.sleep(time);

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

        // Get user choice from the given range
        public static int getUserChoice(int min, int max) {
            int choice;
            do {
                Output.slowPrint("Enter your choice: ");
                while (!scanner.hasNextInt()) {
                    Output.slowPrint("Invalid input. Please enter a number between " + min + " and " + max + ": ");
                    scanner.next();
                }
                choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline character
            } while (choice < min || choice > max);
            return choice;
        }

}