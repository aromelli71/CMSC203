/*
 * Class: CMSC203 CRN 23132
 * Instructor: Professor Kuijt
 * Description: Play a random number guessing game using a provided RNG class which provides random numbers and validates input
 * Due: 09/25/2022
 * Platform/compiler: Eclipse-Java
 * I pledge that I have completed the programming 
 * assignment independently. I have not copied the code 
 * from a student or any source. I have not given my code 
 * to any student.
   Print your Name here: Archer Romelli
*/

import java.util.Scanner;
public class RandomNumberGuesser {

	public static void main(String[] args) {
		Scanner stdin = new Scanner(System.in);
		System.out.print("This application generates a random integer between 0 and 100\n\tand asks the ser to guess repeatedly until they guess correctly\n\n");
		boolean playGame = true, wonGame = false;
		while (playGame) {
			int correctNum = RNG.rand();
			RNG.resetCount();
			int lowestGuess = 0, highestGuess = 100;
			while (RNG.getCount() <= 7) {
				if (RNG.getCount() == 0)
					System.out.println("Enter your first guess:");
				else System.out.println("Enter your next guess between " + lowestGuess + " and " + highestGuess);
				int currentGuess = stdin.nextInt();
				while (!RNG.inputValidation(currentGuess, lowestGuess, highestGuess))
					currentGuess = stdin.nextInt();
				if (currentGuess == correctNum) {
					wonGame = true;
					break;
				} else if (RNG.getCount() > 7)
					break;
				else if (currentGuess < correctNum) {
					System.out.println("Your guess is too low");
					lowestGuess = currentGuess;
				} else if (currentGuess > correctNum) {
					System.out.println("Your guess is too high");
					highestGuess = currentGuess;
				}
				System.out.println("Number of guesses is: " + RNG.getCount());
			}
			if (wonGame) {
				boolean validInput = true;
				System.out.println("Congratulations, you guessed correctly");
				do {
					System.out.println("Try again? (yes or no)");
					String input = stdin.next();
					if (input.compareToIgnoreCase("yes") == 0)
						playGame = true;
					else if (input.compareToIgnoreCase("no") == 0)
						playGame = false;
					else validInput = false;
				} while (!validInput);
			} else {
				playGame = false;
				System.out.println("You have exceeded the maximum number of guesses, 7. Try again.");
			}
		}
		stdin.close();
	}
}
