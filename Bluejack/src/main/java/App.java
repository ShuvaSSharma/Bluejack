import java.util.*;

public class App {
    private static final int TARGET = 21; 
    private static final Random RANDOM = new Random();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to Bluejack! Try to reach as close to 21 as possible.");

        int playerScore = playTurn("Player");
        if (playerScore > TARGET) {
            System.out.println("You busted! Dealer wins.");
            return;
        }

        int dealerScore = playTurn("Dealer");
        if (dealerScore > TARGET) {
            System.out.println("Dealer busted! You win!");
            return;
        }

        determineWinner(playerScore, dealerScore);
        scanner.close();
    }

    private static int playTurn(String playerName) {
        int score = 0;
        Scanner scanner = new Scanner(System.in);

        System.out.println("\n" + playerName + "'s turn:");

        while (true) {
            int card = drawCard();
            score += card;
            System.out.println(playerName + " drew a card with value: " + card + " (Total score: " + score + ")");

            if (score >= TARGET) {
                break;
            }

            if (playerName.equals("Player")) {
                System.out.print("Do you want to draw another card? (yes/no): ");
                String choice = scanner.next().toLowerCase();
                if (!choice.equals("yes")) {
                    break;
                }
            } else {

                if (score >= 17) {
                    System.out.println("Dealer stops drawing.");
                    break;
                }
            }
        }
        return score;
    }

    private static int drawCard() {
        return RANDOM.nextInt(11) + 1;
    }

    private static void determineWinner(int playerScore, int dealerScore) {
        System.out.println("\nFinal Results:");
        System.out.println("Player's Score: " + playerScore);
        System.out.println("Dealer's Score: " + dealerScore);

        if (playerScore > dealerScore) {
            System.out.println("Congratulations! You win!");
        } else if (playerScore < dealerScore) {
            System.out.println("Dealer wins! Better luck next time.");
        } else {
            System.out.println("It's a tie!");
        }
    }
}
