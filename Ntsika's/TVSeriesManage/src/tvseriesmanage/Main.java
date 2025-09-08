package tvseriesmanage;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        SeriesManager manager = new SeriesManager();
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            printMenu();
            System.out.print("Select option: ");
            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    manager.captureSeries();
                    break;
                case 2:
                    manager.searchSeries();
                    break;
                case 3:
                    manager.updateSeries();
                    break;
                case 4:
                    manager.deleteSeries();
                    break;
                case 5:
                    manager.printReport();
                    break;
                case 6:
                    System.out.println("Exiting application... Goodbye!");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid option! Please try again.");
            }
        }
        scanner.close();
    }

    private static void printMenu() {
        System.out.println("\n=== TV Series Manager ===");
        System.out.println("1. Capture a new series");
        System.out.println("2. Search for a series");
        System.out.println("3. Update a series");
        System.out.println("4. Delete a series");
        System.out.println("5. Print full report");
        System.out.println("6. Exit application");
    }
}