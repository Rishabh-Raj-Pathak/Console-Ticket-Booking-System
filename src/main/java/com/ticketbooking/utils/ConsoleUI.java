package com.ticketbooking.utils;

import java.util.List;
import java.util.Scanner;

/**
 * Utility class for console-based user interface.
 */
public class ConsoleUI {
    private static Scanner scanner = new Scanner(System.in);

    public static void printHeader(String title) {
        System.out.println("\n===== " + title + " =====");
    }

    public static void printMenu(String title, List<String> options) {
        printHeader(title);
        for (int i = 0; i < options.size(); i++) {
            System.out.println((i + 1) + ". " + options.get(i));
        }
        System.out.println("0. Back/Exit");
    }

    public static int getMenuChoice(int maxOption) {
        System.out.print("\nEnter your choice (0-" + maxOption + "): ");
        while (true) {
            try {
                int choice = Integer.parseInt(scanner.nextLine());
                if (choice >= 0 && choice <= maxOption) {
                    return choice;
                } else {
                    System.out.print("Invalid choice. Please enter a number between 0 and " + maxOption + ": ");
                }
            } catch (NumberFormatException e) {
                System.out.print("Invalid input. Please enter a number: ");
            }
        }
    }

    public static String getInput(String prompt) {
        System.out.print(prompt + ": ");
        return scanner.nextLine();
    }

    public static <T> T selectFromList(List<T> items, String prompt) {
        if (items.isEmpty()) {
            System.out.println("No items available.");
            return null;
        }

        System.out.println("\n" + prompt + ":");
        for (int i = 0; i < items.size(); i++) {
            System.out.println((i + 1) + ". " + items.get(i));
        }

        int choice = getMenuChoice(items.size());
        if (choice == 0) {
            return null;
        }

        return items.get(choice - 1);
    }

    public static void pressEnterToContinue() {
        System.out.print("\nPress Enter to continue...");
        scanner.nextLine();
    }
}