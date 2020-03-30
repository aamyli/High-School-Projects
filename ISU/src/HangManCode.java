import java.util.*;
public class HangManCode {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		
		System.out.println("Player 1: input a word (max 15 characters)");
		String word = sc.next();
		
		char[] wordarray = word.toCharArray();
		
		int correctletters = word.length();
		int correctguesses = 0;
		int wrongguesses = 0;
		int breaker = 0;
		while (correctguesses != correctletters) {
			System.out.println("Guess a letter: ");
			char guess = sc.next().charAt(0);
			for (int i = 0; i < wordarray.length; i++) {
				if (guess == wordarray[i]) {
					System.out.println("Correct: " + guess);
					correctguesses = correctguesses + 1;
					breaker = breaker + 1;
				}
			}
			if (breaker == 0) {
				System.out.println("Incorrect: " + guess);
				wrongguesses = wrongguesses + 1;
				System.out.println("Wrong guesses: " + wrongguesses);
				//output a body part
			}
			breaker = 0;
			if (wrongguesses >= 10) {
				System.out.println("Game Over. You lose.");
				System.exit(0);
			}
		}
		System.out.println("Congrats! You win.");
		
	}
}
