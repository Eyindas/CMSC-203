/*Class: CMSC203 CRN 30312
 Program: Assignment 1
 Instructor: Ahmed Tarek
 Summary of Description: This program is a game that prompt the user to guess colors
 that are randomly selected from a file
 Due Date: 02/10/2025 
 Integrity Pledge: I pledge that I have completed the programming assignment independently.
 I have not copied the code from a student or any source.
Student’s Name: Elberth Ndounou Yindas
*/


import java.io.File;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.Random;
import java.io.FileNotFoundException;

public class ESPGame {
	
	public static void main(String[] args) {
		final String FILENAME = "colors.txt"; // define the filename
		
		Scanner scanner = new Scanner(System.in); // creation of the scanner to read use input
		
		try (Scanner input = new Scanner(System.in)) {
			Random rand = new Random(); //to randomize the choice of color
			StringBuilder gameResult = new StringBuilder(); //this line is used to help store game results
			
			boolean playAgain = true;
			while (playAgain) {
				System.out.println("\nCMSC203 Assignment1: Test your ESP skills!");
				System.out.println("Welcome to ESP - extrasensory perception!");
				System.out.println("Would you please choose one of the 4 options from the menu:");
				System.out.println("1- Read and display on the screen first 16 names of colors ");
				System.out.println("2- Read and display on the screen first 10 names of colors ");
				System.out.println("3- Read and display on the screen first 5 names of colors ");
				System.out.println("4- Exit form a program ");
				System.out.println("Choose one of the four options: "); // Display of the choice
				
				int choice = input.nextInt();
				input.nextLine();
				
				if (choice == 4) break; //Exit if user choose 4
				
				System.out.print("Enter the name of the file: ");
				String filename = scanner.nextLine(); // get name of the file
				
				if (!filename.equals(FILENAME)) {
					System.out.println("The name of the file is invalid. please use" + FILENAME );
					continue; 
				}
				
				int limit = (choice == 1) ? 16 : (choice == 2) ? 10 : 5;
				String[] colors = new String[limit];
				
				//read color from the file
				try (Scanner fileScanner = new Scanner(new File("src/colors.txt"))) { 
					for (int i = 0; i < limit && fileScanner.hasNextLine(); i++)
						colors[i] = fileScanner.nextLine().trim().toLowerCase();
				} catch(FileNotFoundException e) {
				System.out.println("The file colors.txt is not found. ");
				continue;
				}
				
				System.out.println("\nColors available: ");
				for (String color : colors) System.out.println(color);
				
				//promt the three rounds of guesses
				int rightGuesses = 0;
				for (int i = 1; i <= 3; i++) {
					String colorSelected = colors[rand.nextInt(colors.length)]; // randomize the choice of color
					System.out.println("Round " + i + ": Guess the write color: ");
					String guessUser = input.nextLine().trim().toLowerCase();
					
					if (guessUser.equals(colorSelected)) {
						System.out.println("Correct choice!");
						rightGuesses++;
					} else {
						System.out.println("Wrong! the good choice was " + colorSelected + ".");
							
					}
					
					
				}
				
				System.out.println("\nGame over. You guess " + rightGuesses + " out of 3 correctly. "); // Display game results
				gameResult.append("Game Result: " ).append(rightGuesses).append("out of 3 correct.\n ");
				
				System.out.println("Do you want to play again ? (yes/no): ");
				String response = input.nextLine().trim().toLowerCase();
				playAgain = response.equals("yes");
				
				}
				
			//save game results
			try (PrintWriter writer = new PrintWriter("EspGameResult.txt")) {
			
				System.out.println("What is your name: ");
				String userName = input.nextLine();
				System.out.println("Describel yourself: ");
				String description = input.nextLine();
				System.out.println("Due date (MM/DD");
				String dueDate = input.nextLine();
				
				//write result to the file
				writer.println("Game over");
				writer.println("Due date: " + dueDate);
				writer.println("Username: " + userName);
				writer.println("User Description: " + description);
				writer.println("-------Game result-------");
				writer.println(gameResult);
				
				System.out.println("Results have been saved to EspGameResults.txt.");
			} catch (FileNotFoundException e) {
				System.out.println("An error occured while writing results.");
				
			}
		
			System.out.println("Thank you for playing!"); // Automatic closure of the scanner
			
		}
	}
}
	



			
		
			
					
					
					
					              