package view;

import java.util.InputMismatchException;
import java.util.Scanner;

public class FileView {
    private Scanner scanner = new Scanner(System.in);

    public void printChoices() {
        System.out.println("Menudsadasdasdasdas:");
        System.out.println("1. Add new user");
        System.out.println("2. Select user");
        System.out.println("3. Show current user's name");
        System.out.println("4. Change username");
        System.out.println("5. Delete current user");
        System.out.println("6. List current user's character sheets");
        System.out.println("7. Make new character sheet");
        System.out.println("8. View existing character sheet");
        System.out.println("9. Edit existing character sheet");
        System.out.println("10. Delete existing character sheet");
        System.out.println("11. Exit");
    }

    public int getMenuChoice() {
        printChoices();
        int choice = -1;
        try {
            choice = scanner.nextInt();
        } catch (InputMismatchException e) {
            e.printStackTrace();
            System.out.println("Invalid input.");
            printChoices();
            scanner.nextLine(); // Clear the invalid input
        }
        scanner.nextLine();  // Consume the newline character
        return choice;
    }

    public int getIntAnswer() {
        int choice = -1;
        boolean validInput = false;

        System.out.print("Enter the number corresponding to your choice: ");
        do {
            try {
                choice = scanner.nextInt();
                validInput = true; // If no exception, input is valid
            } catch (InputMismatchException e) {
                System.out.print("Invalid input. Please enter a number: ");
            }
            scanner.nextLine(); // Consume the newline character
        } while (!validInput);

        return choice;
    }

    public String getFileName(String prompt) {
        System.out.print(prompt);
        String fileName = scanner.nextLine();
        fileName = fileName + ".txt";
        return fileName;
    }

    public String getUserInput(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }

    public void displayMessage(String message) {
        System.out.println(message);
    }

    public void closeScanner() {
        scanner.close();
    }
}
