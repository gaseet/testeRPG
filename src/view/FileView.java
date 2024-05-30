package view;
import java.util.InputMismatchException;
import java.util.Scanner;

public class FileView {
    private Scanner scanner = new Scanner(System.in);

    public void printChoices() {
        System.out.println("Choose an option:");
        System.out.println("1. Add new user");
        System.out.println("2. Select user");
        System.out.println("3. Show current user's name");
        System.out.println("4. Delete current user");
        System.out.println("5. List current user's character sheets");
        System.out.println("6. Make new character sheet");
        System.out.println("7. View existing character sheet");
        System.out.println("8. Edit existing character sheet");
        System.out.println("9. Delete existing character sheet");
        System.out.println("10. Exit");
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

    public int getClassChoice() {
        System.out.print("Enter your choice: ");
        int choice = -1;
        try {
            choice = scanner.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a number.");
            scanner.nextLine(); // Clear the invalid input
        }
        scanner.nextLine(); // Consume the newline character
        return choice;
    }

    public String getFileName(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
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
