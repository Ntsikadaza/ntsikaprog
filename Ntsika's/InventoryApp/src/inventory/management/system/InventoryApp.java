import java.util.Arrays;
import java.util.Scanner;

public class InventoryApp {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Create inventory
        StockItem[] inventory = new StockItem[5];
        inventory[0] = new StockItem(101, "Laptop", 12000, 5);
        inventory[1] = new StockItem(102, "Mouse", 150, 50);
        inventory[2] = new StockItem(103, "Keyboard", 350, 20);
        inventory[3] = new StockItem(104, "Monitor", 2000, 10);
        inventory[4] = new StockItem(105, "Printer", 2500, 8);

        int choice;
        do {
            System.out.println("\n=== INVENTORY MENU ===");
            System.out.println("1. View Inventory Report");
            System.out.println("2. Search Product by ID");
            System.out.println("3. Sort Products by Price");
            System.out.println("4. Show Total Stock Value");
            System.out.println("5. Exit");
            System.out.print("Enter choice: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("\n=== INVENTORY REPORT ===");
                    for (StockItem item : inventory) {
                        System.out.println(item);
                    }
                    break;

                case 2:
                    System.out.print("Enter Product ID to search: ");
                    int id = sc.nextInt();
                    StockItem found = searchById(inventory, id);
                    if (found != null) {
                        System.out.println("Found: " + found);
                    } else {
                        System.out.println("Product not found.");
                    }
                    break;

                case 3:
                    Arrays.sort(inventory, (a, b) -> Double.compare(a.getPrice(), b.getPrice()));
                    System.out.println("\n=== SORTED BY PRICE ===");
                    for (StockItem item : inventory) {
                        System.out.println(item);
                    }
                    break;

                case 4:
                    System.out.println("Total Stock Value: " + getTotalStockValue(inventory));
                    break;

                case 5:
                    System.out.println("Exiting... Goodbye!");
                    break;

                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        } while (choice != 5);

        sc.close();
    }

    // Calculate total stock value
    public static double getTotalStockValue(StockItem[] items) {
        double total = 0;
        for (StockItem item : items) {
            total += item.getStockValue();
        }
        return total;
    }

    // Search product by ID
    public static StockItem searchById(StockItem[] items, int id) {
        for (StockItem item : items) {
            if (item.getId() == id) {
                return item;
            }
        }
        return null;
    }
}