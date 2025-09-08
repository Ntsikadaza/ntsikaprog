package tvseriesmanage;

import java.util.ArrayList;
import java.util.Scanner;

public class SeriesManager {

    private ArrayList<SeriesModel> seriesList;
    private int nextId;
    private Scanner scanner;

    public SeriesManager() {
        seriesList = new ArrayList<>();
        nextId = 1;
        scanner = new Scanner(System.in);
    }

    // ====== Interactive Methods (Console) ======
    public void captureSeries() {
        System.out.println("\n--- Capture New Series ---");
        System.out.print("Enter series name: ");
        String name = scanner.nextLine();
        int ageRestriction = getValidatedAgeRestriction();
        System.out.print("Enter number of episodes: ");
        int episodes = Integer.parseInt(scanner.nextLine());

        SeriesModel newSeries = new SeriesModel(nextId++, name, ageRestriction, episodes);
        seriesList.add(newSeries);
        System.out.println("Series added successfully! ID: " + newSeries.getId());
    }

    public void searchSeries() {
        System.out.print("\nEnter series ID to search: ");
        int id = Integer.parseInt(scanner.nextLine());
        SeriesModel s = searchSeries(id);
        if (s != null) {
            printSeriesDetails(s);
        } else {
            System.out.println("Series not found!");
        }
    }

    public void updateSeries() {
        System.out.print("\nEnter series ID to update: ");
        int id = Integer.parseInt(scanner.nextLine());
        SeriesModel s = searchSeries(id);
        if (s != null) {
            System.out.println("What would you like to update?");
            System.out.println("1. Name");
            System.out.println("2. Age Restriction");
            System.out.println("3. Number of Episodes");
            System.out.print("Select option: ");
            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    System.out.print("Enter new name: ");
                    s.setName(scanner.nextLine());
                    break;
                case 2:
                    s.setAgeRestriction(getValidatedAgeRestriction());
                    break;
                case 3:
                    System.out.print("Enter new episode count: ");
                    s.setEpisodes(Integer.parseInt(scanner.nextLine()));
                    break;
                default:
                    System.out.println("Invalid option!");
                    return;
            }
            System.out.println("Series updated successfully!");
        } else {
            System.out.println("Series not found!");
        }
    }

    public void deleteSeries() {
        System.out.print("\nEnter series ID to delete: ");
        int id = Integer.parseInt(scanner.nextLine());
        SeriesModel s = searchSeries(id);
        if (s != null) {
            System.out.print("Are you sure you want to delete this series? (y/n): ");
            if (scanner.nextLine().equalsIgnoreCase("y")) {
                seriesList.remove(s);
                System.out.println("Series deleted successfully!");
            }
        } else {
            System.out.println("Series not found!");
        }
    }

    public void printReport() {
        System.out.println("\n--- Full Series Report ---");
        if (seriesList.isEmpty()) {
            System.out.println("No series found!");
            return;
        }
        for (SeriesModel s : seriesList) {
            printSeriesDetails(s);
            System.out.println("----------------------");
        }
    }

    // ====== Parameterized Methods (For Unit Testing) ======
    public void captureSeries(SeriesModel newSeries) {
        newSeries.setId(nextId++);
        seriesList.add(newSeries);
    }

    public SeriesModel searchSeries(int id) {
        for (SeriesModel s : seriesList) {
            if (s.getId() == id) return s;
        }
        return null;
    }

    public boolean updateSeries(int id, String newName, int newAge, int newEpisodes) {
        SeriesModel s = searchSeries(id);
        if (s != null) {
            s.setName(newName);
            s.setAgeRestriction(newAge);
            s.setEpisodes(newEpisodes);
            return true;
        }
        return false;
    }

    public boolean deleteSeries(int id) {
        SeriesModel s = searchSeries(id);
        if (s != null) {
            seriesList.remove(s);
            return true;
        }
        return false;
    }

    public String printReportString() {
        if (seriesList.isEmpty()) return "No series found!";
        StringBuilder sb = new StringBuilder();
        for (SeriesModel s : seriesList) {
            sb.append("ID: ").append(s.getId())
              .append(" | Name: ").append(s.getName())
              .append(" | Age: ").append(s.getAgeRestriction())
              .append(" | Episodes: ").append(s.getEpisodes())
              .append("\n");
        }
        return sb.toString();
    }

    // ====== Helper Methods ======
    private int getValidatedAgeRestriction() {
        while (true) {
            try {
                System.out.print("Enter age restriction (2-18): ");
                int age = Integer.parseInt(scanner.nextLine());
                if (age >= 2 && age <= 18) return age;
                System.out.println("Please enter a value between 2 and 18.");
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }
    }

    private void printSeriesDetails(SeriesModel s) {
        System.out.println("ID: " + s.getId());
        System.out.println("Name: " + s.getName());
        System.out.println("Age Restriction: " + s.getAgeRestriction());
        System.out.println("Episodes: " + s.getEpisodes());
    }

    public ArrayList<SeriesModel> getSeriesList() {
        return seriesList;
    }
}