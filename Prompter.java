package net.snortum.prompter;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

/**
 * A collection of methods that will prompt for and get data from the console
 * 
 * @author Knute Snortum
 * @version 2016.05.18
 */
public class Prompter {
	
	private final static String YES_NO_PROMPT = 
			".*"       // any number of any character
			+ "\\("    // a literal "("
			+ "[^)]+"  // one or more characters that is not ")"
			+ "\\)"    // a literal ")"
			+ ":\\s*"; // a ":" and any number of whitespaces
	
	private Scanner scan;
	
	/**
	 * Get a new Prompter object
	 */
	public Prompter() {
		scan = new Scanner(System.in);
	}
	
	/**
	 * Prompt for and get a String.  An empty string is valid.
	 * 
	 * If it doesn't already have it, a ": " will be added to the end of the prompt.
	 * 
	 * @param prompt the prompt to be displayed
	 * @return the entered String, possibly empty
	 */
	public String getStringOrEmpty(String prompt) {
		printPrompt(prompt);
		return scan.nextLine();
	}
	
	/**
	 * Prompt for a get a String.  It cannot be empty.
	 * 
	 * If it doesn't already have it, a ": " will be added to the end of the prompt.
	 * 
	 * @param prompt the prompt to be displayed
	 * @return the entered String, never empty
	 */
	public String getString(String prompt) {
		String result = "";
		
		do {
			result = getStringOrEmpty(prompt);
		} while (result.isEmpty());
		
		return result;
	}
	
	/**
	 * Prompt for and get either a yes or no response.  Any response that starts with
	 * "y", "Y", "n", or "N" is accepted.
	 * 
	 * If the prompt does not end with something in parentheses, " (y/n): " will be
	 * added to the end of the prompt.
	 * 
	 * @param prompt the prompt to be displayed
	 * @return "y" or "n"
	 */
	public String getYN(String prompt) {
		if (!prompt.matches(YES_NO_PROMPT)) {
			prompt = prompt + " (y/n): ";
		}
		String result = "";
		
		do {
			result = getString(prompt);
			result = result.toLowerCase();
		} while (result.charAt(0) != 'y' && result.charAt(0) != 'n');
		
		return result.substring(0, 1);
	}
	
	/**
	 * Prompt and get a string from a List of valid responses.  Will redisplay the prompt
	 * until a valid response is entered.
	 * 
	 * If it doesn't already have it, a ": " will be added to the end of the prompt.
	 * 
	 * @param prompt the prompt to display
	 * @param options a list of valid responses
	 * @return a valid response from {@code options}
	 */
	public String getFromValidList(String prompt, List<String> options) {
		String result = "";
		
		do {
			result = getString(prompt);
		} while (!options.contains(result));
		
		return result;
	}
	
	/**
	 * Prompt for and return a valid integer.  Valid integers may have thousands
	 * separators.  
	 * 
	 * If it doesn't already have it, a ": " will be added to the end of the prompt.
	 * 
	 * @param prompt the prompt to display
	 * @return a valid integer
	 */
	public int getInt(String prompt) {
		boolean reprompt;
		int result = 0;
		
		do {
			reprompt = false;
    		printPrompt(prompt);
    		try {
				result = scan.nextInt();
			} catch (InputMismatchException e) {
				System.out.println("Enter a valid integer");
				reprompt = true;
			} 
    		scan.nextLine(); // Consume CR/LF
		} while (reprompt);
		
		return result;
	}
	
	/**
	 * Prompt for and return a valid long.  Valid longs may have thousands
	 * separators.  
	 * 
	 * If it doesn't already have it, a ": " will be added to the end of the prompt.
	 * 
	 * @param prompt the prompt to display
	 * @return a valid long
	 */
	public long getLong(String prompt) {
		boolean reprompt;
		long result = 0;
		
		do {
			reprompt = false;
    		printPrompt(prompt);
    		try {
				result = scan.nextLong();
			} catch (InputMismatchException e) {
				System.out.println("Enter a valid long integer");
				reprompt = true;
			} 
    		scan.nextLine(); // Consume CR/LF
		} while (reprompt);
		
		return result;
	}
	
	/**
	 * Prompt for and return a valid double.  Valid doubles may have thousands
	 * separators.  
	 * 
	 * If it doesn't already have it, a ": " will be added to the end of the prompt.
	 * 
	 * @param prompt the prompt to display
	 * @return a valid double
	 */
	public double getDouble(String prompt) {
		boolean reprompt;
		double result = 0.0;
		
		do {
			reprompt = false;
    		printPrompt(prompt);
    		try {
				result = scan.nextDouble();
			} catch (InputMismatchException e) {
				System.out.println("Enter a valid decimal");
				reprompt = true;
			} 
    		scan.nextLine(); // Consume CR/LF
		} while (reprompt);
		
		return result;
	}
	
	/**
	 * Display "Press <Enter> to continue:" and wait for
	 * any input, including an empty input.
	 */
	public void pause() {
		getStringOrEmpty("");
	}
	
	/**
	 * Prints {@code prompt}, checking whether it ends with ":".
	 * Changes an empty prompt to "Press <Enter> to continue:"
	 * 
	 * @param prompt the prompt to display
	 */
	private void printPrompt(String prompt) {
		if (prompt == null || prompt.isEmpty()) {
			prompt = "Press <Enter> to continue: ";
		} else if (!prompt.endsWith(":") 
				&& !prompt.endsWith(": ")
				&& !prompt.endsWith(">")) {
			prompt = prompt + ": ";
		}
		System.out.print(prompt);
	}
}
