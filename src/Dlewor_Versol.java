import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Dlewor_Versol {
    public static void main(String[] args) {
        String filename = null;
        FileInputStream myFile = null;

        // use first command line argument to open dictionary file,
        // gracefully exits if no file
        if (args.length == 1) {
            filename = args[0];
            try {
                myFile = new FileInputStream(filename);
            } catch (FileNotFoundException e) {
                System.err.println("Could not open input file.");
            }
        } else {
            System.err.println("Wordle [ filename ]");
            System.exit(-1);
        }

        // read in a dictionary file with all words that contain exactly 5 characters
        ArrayList<String> wordlist = new ArrayList<String>();
        Scanner fileReader = new Scanner(myFile); //set the scanner to read from a file
        while (fileReader.hasNextLine()) {
            String word = fileReader.nextLine();
            if (word.length() == 5) {
                wordlist.add(word);
            }
        }

        // print welcome message
        System.out.println("Welcome to Dlewor(TM) Versol");

        int[] match = new int[5];
        Scanner input = new Scanner(System.in);
        // enter the guessed word
        for (int i = 0; i < 6; i++) {
            System.out.print("Enter your guessed word (" + (i + 1) + "): ");
            String guess = input.nextLine();

            // check if the word exists in the dictionary
            if (!wordlist.contains(guess)) {
                System.out.println("The word \"" + guess + "\" is not a possible choice. Please try again.");
                i--;
                continue;
            }
            // enter the result of the guess
            boolean correct = false;
            while (!correct) {
                for (int j = 0; j < guess.length(); j++) {
                    System.out.println("What was the result for " + guess.charAt(j) + ": [g]reen, [y]ellow, or [b]lack");
                    String result = input.nextLine();
                    if (result.equalsIgnoreCase("g")) {
                        match[j] = 2;
                    } else if (result.equalsIgnoreCase("y")) {
                        match[j] = 1;
                    } else {
                        match[j] = 0;
                    }
                }

                // write guess to screen
                System.out.print("You entered: ");
                for (int j = 0; j < guess.length(); j++) {
                    System.out.print("[" + guess.charAt(j) + " - ");
                    if (match[j] == 2) {
                        System.out.print("green");
                    } else if (match[j] == 1) {
                        System.out.print("yellow");
                    } else {
                        System.out.print("black");
                    }
                    System.out.print("] ");
                }
                System.out.println();

                // check if user correctly entered the information
                System.out.println("Is this correct? (y/n)");
                String response = input.nextLine();
                if (response.equalsIgnoreCase("y")) {
                    correct = true;
                }
            }

            // TODO: check if each character is in the word at correct location

            // TODO: check if character is in the word anywhere

            // TODO: check if character does not exist in the word anywhere

            // print out possible answers
            System.out.println("These are possible answers: ");
            for (int j = 0; j < wordlist.size(); j++) {
                System.out.println(wordlist.get(j));
            }
        }
    }
}
