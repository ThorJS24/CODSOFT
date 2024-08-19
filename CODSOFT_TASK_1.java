import java.util.*;

public class CODSOFT_TASK_1 {

    public static void playRound(Scanner scanner) {
        // Generate a random number between 1 and 100
        Random random = new Random();
        int numberToGuess = random.nextInt(100) + 1;

        // Set the number of attempts allowed
        int maxAttempts = 10;
        int attempts = 0;

        while (attempts < maxAttempts) {
            System.out.print("Enter your guess (1-100): ");
            
            // Handle input and validate
            int guess;
            try {
                guess = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid integer.");
                continue;
            }

            attempts++;

            // Provide feedback on the guess
            if (guess < numberToGuess) {
                System.out.println("Too low!");
            } else if (guess > numberToGuess) {
                System.out.println("Too high!");
            } else {
                System.out.println("Congratulations! You guessed the number in " + attempts + " attempts.");
                return;
            }
        }

        System.out.println("Sorry, you've used all " + maxAttempts + " attempts. The number was " + numberToGuess + ".");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int totalRounds = 0;
        int roundsWon = 0;

        while (true) {
            System.out.println("\nStarting a new round...");
            playRound(scanner);
            totalRounds++;

            // Ask the user if they want to play another round
            System.out.print("Do you want to play again? (yes/no): ");
            String playAgain = scanner.nextLine().trim().toLowerCase();
            if (playAgain.equals("yes")) {
                roundsWon++;
            } else {
                break;
            }
        }

        // Display the final score
        System.out.println("\nGame Over! You played " + totalRounds + " rounds and won " + roundsWon + " times.");

        // Close the scanner to avoid resource leak
        scanner.close();
    }
}
