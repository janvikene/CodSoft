import java.util.Random;
import java.util.Scanner;

public class NumberGuessingGame {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Random random = new Random();

        int totalRounds = 0;
        int roundsWon = 0;
        int totalAttempts = 0;

        System.out.println("======================================");
        System.out.println("      WELCOME TO GUESS THE NUMBER");
        System.out.println("======================================");

        char playAgain = 'Y';

        while (playAgain == 'Y' || playAgain == 'y') {

            totalRounds++;

            int randomNumber = random.nextInt(100) + 1;
            int maxAttempts = 7;
            int attempts = 0;
            boolean guessedCorrectly = false;

            System.out.println("\n--------------------------------------");
            System.out.println("Round " + totalRounds);
            System.out.println("I have selected a number between 1 and 100.");
            System.out.println("You have only " + maxAttempts + " attempts.");
            System.out.println("--------------------------------------");

            while (attempts < maxAttempts) {

                System.out.print("Enter your guess: ");
                int guess = sc.nextInt();

                attempts++;

                if (guess == randomNumber) {

                    System.out.println("\nCongratulations!");
                    System.out.println("You guessed the correct number.");
                    System.out.println("Attempts Taken : " + attempts);

                    guessedCorrectly = true;
                    roundsWon++;
                    totalAttempts += attempts;
                    break;

                } else if (guess < randomNumber) {

                    System.out.println("Too Low! Try a bigger number.");

                } else {

                    System.out.println("Too High! Try a smaller number.");

                }

                System.out.println("Remaining Attempts : " + (maxAttempts - attempts));
                System.out.println();
            }

            if (!guessedCorrectly) {

                totalAttempts += maxAttempts;

                System.out.println("\nYou have used all your attempts.");
                System.out.println("The correct number was : " + randomNumber);
            }

            System.out.println("\nDo you want to play another round?");
            System.out.print("Enter Y for Yes or N for No : ");
            playAgain = sc.next().charAt(0);
        }

        System.out.println("\n======================================");
        System.out.println("            GAME SUMMARY");
        System.out.println("======================================");

        System.out.println("Total Rounds Played : " + totalRounds);
        System.out.println("Rounds Won          : " + roundsWon);
        System.out.println("Rounds Lost         : " + (totalRounds - roundsWon));

        if (roundsWon > 0) {

            double averageAttempts = (double) totalAttempts / roundsWon;

            System.out.printf("Average Attempts (Winning Rounds) : %.2f\n", averageAttempts);
        }

        int score = roundsWon * 100;

        System.out.println("Final Score : " + score);

        if (score >= 300) {
            System.out.println("Excellent Performance!");
        } else if (score >= 200) {
            System.out.println("Very Good!");
        } else if (score >= 100) {
            System.out.println("Good Job! Keep Practicing.");
        } else {
            System.out.println("Better Luck Next Time!");
        }

        System.out.println("\nThank you for playing.");
        sc.close();
    }
}
