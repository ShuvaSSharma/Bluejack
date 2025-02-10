import java.util.*;

/* 
Today, I'll be recreating one of my favorite card games: Blackjack. Going to call this project 'Bluejack' because I was sad I lost the game the day I decided to recreate it (haha).
The rules (if you're unfamiliar):

    Objective: Get closer to 21 than the dealer without exceeding 21.
    Card Values: Number cards = face value, face cards = 10, Ace = 1 or 11.
    Start: Players and dealer get 2 cards; one of the dealer’s cards is face up.
    Player Options:
    Hit: Take a card.
    Stand: Keep current hand.
    Double Down: Double bet, take one card.
    Split: Divide matching cards into two hands.
    Dealer: Must hit until at least 17; stands on 17+.
    Winning: Closest to 21 without busting (over 21) wins. Blackjack (Ace + 10) beats other 21s.
    Bust: Exceed 21 and lose. Tie with dealer = push (no winner).

*/

public class App {
    private static final int TARGET = 21; // need to declare these values as final, no more 'magic #'s' 
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
        scanner.close(); // prevents further user input
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
