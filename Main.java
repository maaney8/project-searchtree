import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		// Prompt the user for a file name
		Scanner scanner = new Scanner(System.in);
		System.out.print("Please enter the name of a file: ");
		String fileName = scanner.nextLine();

		File file = new File(fileName);

		// read the file and construct the map
		// stores the string
		List<String> lines = new ArrayList<>();
		// stores the occurrences of words in the file
		CustomBinaryTreeSimpleMap<String, List<Integer>> map = new CustomBinaryTreeSimpleMap<>();
		try (Scanner fileScanner = new Scanner(new File(fileName))) {
			int lineNo = 0;
			while (fileScanner.hasNextLine()) {
				// read the next line
				String line = fileScanner.nextLine();
				// increment the line number
				lineNo++;
				// add to the list of lines
				lines.add(line);
				// remove punctuation
				line = line.replaceAll("[^a-zA-Z ]", "");
				// convert to lower case
				line = line.toLowerCase();
				// split into words
				String[] words = line.split(" ");
				// add to the map
				for (String word : words) {
					// trim leading and trailing whitespace
					word = word.trim();
					// skip empty words
					if (word.length() > 0) {
						// check if the word is already in the map
						if (map.containsKey(word)) {
							// get the list of line numbers
							// and add the current line number
							map.get(word).add(lineNo);
						} else {
							// create a new list of line numbers
							List<Integer> list = new ArrayList<>();
							// add the current line number
							list.add(lineNo);
							// add the word and the list to the map
							map.put(word, list);
						}
					}
				}
			}
		} catch (IOException e) {
			System.out.println("File not found: " + fileName);
			return;
		}

		// prompt the user for a word
		System.out.println("Please enter a search term (blank to exit):");
		String word = scanner.nextLine();
		while (word.length() > 0) {
			// remove punctuation
			word = word.replaceAll("[^a-zA-Z ]", "");
			// convert to lower case
			word = word.toLowerCase();
			// trim leading and trailing spaces
			word = word.trim();
			// search for the word
			if (map.containsKey(word)) {
				List<Integer> list = map.get(word);
				System.out.println("Found " + word + " on line(s): ");
				for (int lineNo : list) {
					System.out.println(lineNo + ": " + lines.get(lineNo - 1));
				}
			} else {
				System.out.println("Word not found");
			}
			// prompt the user for another word
			System.out.println("Please enter a search term (blank to exit):");
			word = scanner.nextLine();
		}
	}
}