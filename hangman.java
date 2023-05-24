package hangmanByJohn;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class hangman {

	public static void main(String[] args) throws FileNotFoundException {
		
		
		Scanner userInput = new Scanner(System.in);
		System.out.println("1 or 2 player?");
		String player = userInput.nextLine();
		String word;
		if(player.equals("1")) {

			Scanner scanner = new Scanner(new File("C:\\Users\\Jeeva\\Documents\\GitHub\\Hangman\\words.txt"));			
			
			List<String> words = new ArrayList<>();
			
			while(scanner.hasNext()) {
				words.add(scanner.nextLine());
			}
			
			Random rand = new Random();
			word = words.get(rand.nextInt(words.size()));
		}
		else {
			System.out.println("Player 1, please enter your word: ");
			word = userInput.nextLine();
			System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
			System.out.println("Ready for player2, Good luck :)");
		}
		
		
		
	//	System.out.println(word);
		
		List<Character> playerGuesses = new ArrayList<>();
		
		int wrongCount = 0;
		
		
		while(true){
			
			printHangedMan(wrongCount);
			if(wrongCount >= 5) {
				System.out.println("You dead! And the word was " + word);
				break;
			}
			
			printCurrentStateOfWord(word, playerGuesses);
			if(!getPlayerGuess(userInput, word, playerGuesses)) {
				wrongCount++;
			}
			
			if(printCurrentStateOfWord(word,playerGuesses)) {
				System.out.println("Yay! You guessed the word, " + word + "!!");
				break;
			}
			
			System.out.println("Please enter your guess for the word: ");
			if(userInput.nextLine().equals(word)) {
				System.out.println("Yay! You guessed the word, " + word + "!!");
				break;
			}
			else {
				System.out.println("Nope guess again!");
			}
		}
	}

	private static void printHangedMan(int wrongCount) {
		
		System.out.println(" -------");
		System.out.println(" |     |");
		if(wrongCount >= 1) {
			System.out.println(" O");
		}
		
		if(wrongCount >= 2) {
			System.out.print("/|");
			if(wrongCount >= 3) {
				System.out.println("\\");
			}
			else {
				System.out.println("");
			}
		}
		
		if(wrongCount >= 4) {
			System.out.print("/ ");
			if(wrongCount >= 5) {
				System.out.println("\\");
			}
			else {
				System.out.println("");
			}
		}
		System.out.println("");
	}


	private static boolean getPlayerGuess(Scanner userInput, String word, List<Character> playerGuesses) {
		System.out.println("Guess a letter : ");
		String letterGuess = userInput.nextLine();
		playerGuesses.add(letterGuess.charAt(0));
		
		return (word.contains(letterGuess));
	}
	
	

	private static boolean printCurrentStateOfWord(String word, List<Character> playerGuesses) {
		
		int correctCount = 0;
		
		for( int i = 0; i < word.length(); i++ ) {
			
			if (playerGuesses.contains(word.charAt(i))) {
				System.out.print(word.charAt(i));
				correctCount++;
			} else {
				System.out.print("-");
				
			}
		}	
		System.out.println();
		return (correctCount == word.length());
	}

}
