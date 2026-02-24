import java.util.Random;
import java.util.Scanner;

public class KeoBuaBao {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        String[] choices = {"rock", "paper", "scissors"};

        
        System.out.print("Enter your choice (rock, paper, scissors): ");
        String userChoice = scanner.nextLine().toLowerCase();

        
        if (!userChoice.equals("rock") &&
            !userChoice.equals("paper") &&
            !userChoice.equals("scissors")) {

            System.out.println("Invalid choice!");
             return;
        }

        
        String computerChoice = choices[random.nextInt(3)];
        System.out.println("Computer chose: " + computerChoice);

        // Game logic
        if (userChoice.equals(computerChoice)) {
            System.out.println("Draw");
        } else if (
                (userChoice.equals("rock") && computerChoice.equals("scissors")) ||
                (userChoice.equals("paper") && computerChoice.equals("rock")) ||
                (userChoice.equals("scissors") && computerChoice.equals("paper"))
        ) {
            System.out.println("win");
        } else {
            System.out.println("lose");
        }

        scanner.close();
    }
}
