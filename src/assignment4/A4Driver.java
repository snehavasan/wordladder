/**
 * Main function that ties all objects+logic together
 * Solves EE422C programming assignment #4
 * @authors Sneha Vasantharao, Jai Bock Lee
 * @version 1.1 2016-3-1
 * 
 * UTEID: sv8398, jbl932
 * Lab Section: 11-12:30pm, Lisa Hua
 * 
 */

package assignment4;

import java.util.*;
import java.io.*;

public class A4Driver {

	// create a dictionary to store all the words
	static HashMap<Character, ArrayList<String>> dictionary = new HashMap<Character, ArrayList<String>>();

	public static void main(String[] args) {
		// make a dictionary first
		if (args.length != 1) {
			System.err.println("Error: Incorrect number of command line arguments");
			System.exit(-1);
		}
		processLinesInFile(args[0]);

		// Create a word ladder solver object
		Assignment4Interface wordLadderSolver = new WordLadderSolver(dictionary);

		try {
			List<String> result = wordLadderSolver.computeLadder("money", "honey");
			boolean correct = wordLadderSolver.validateResult("money", "honey", result);
		} catch (NoSuchLadderException e) {
			e.printStackTrace();
		}
	}

	public static void processLinesInFile(String filename) {

		try {
			FileReader dict_file = new FileReader(filename);
			BufferedReader reader = new BufferedReader(dict_file);

			for (String s = reader.readLine(); s != null; s = reader.readLine()) {
				if (s.charAt(0) != '*') {
					String word = s.substring(0, 5);
					char key = s.charAt(0);
					if (dictionary.containsKey(key)) {
						dictionary.get(key).add(word);
					} else {
						ArrayList<String> newword = new ArrayList<String>();
						newword.add(word);
						dictionary.put(key, newword);
					}
				}

			}

		} catch (FileNotFoundException e) {
			System.err.println("Error: File not found. Exiting...");
			e.printStackTrace();
			System.exit(-1);
		} catch (IOException e) {
			System.err.println("Error: IO exception. Exiting...");
			e.printStackTrace();
			System.exit(-1);
		}
	}
}
