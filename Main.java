import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;
class User {
    private String userId="john";
    private String password="password123";
    public User(String userId, String password) {
        this.userId = userId;
        this.password = password;
    }
    public String getUserId() {
        return userId;
    }
    public String getPassword() {
        return password;
    }
}
class ReservationSystem {
    private Map<String, User> users;
    private Map<String, String> reservations;
    public ReservationSystem() {
        users = new HashMap<>();
        reservations = new HashMap<>();
    }
    public void start() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to the Online Train Reservation System!");
        while (true) {
            System.out.print("User ID: ");
            String userId = scanner.nextLine();
            System.out.print("Password: ");
            String password = scanner.nextLine();
            if (authenticateUser(userId, password)) {
                System.out.println("Authentication successful.");
                break;
            } else {
                System.out.println("Invalid user ID or password. Please try again.");
            }
        }
        showMenu(scanner);
    }
    private boolean authenticateUser(String userId, String password) {
        User user = users.get(userId);
        return user != null && user.getPassword().equals(password);
    }
    private void showMenu(Scanner scanner) {
        while (true) {
            System.out.println("\nSelect an option:");
            System.out.println("1. Register Ticket");
            System.out.println("2. Cancel Ticket");
            System.out.println("3. Logout");
            System.out.print("Option: ");
            int option = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            switch (option) {
                case 1:
                    registerTicket(scanner);
                    break;
                case 2:
                    cancelTicket(scanner);
                    break;
                case 3:
                    System.out.println("Logged out successfully.");
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
                    break;
            }
        }
    }
    private void registerTicket(Scanner scanner) {
        System.out.print("Enter passenger name: ");
        String passengerName = scanner.nextLine();
        System.out.print("Enter source station: ");
        String sourceStation = scanner.nextLine();
        System.out.print("Enter destination station: ");
        String destinationStation = scanner.nextLine();
        String pnr = generatePNR();
        reservations.put(pnr, passengerName);
        System.out.println("Ticket registered successfully.");
        System.out.println("PNR: " + pnr);
    }
    private String generatePNR() {
        Random random = new Random();
        int pnrNumber = random.nextInt(900000) + 100000; // Generate a 6-digit random number
        return "PNR" + pnrNumber;
    }
    private void cancelTicket(Scanner scanner) {
        System.out.print("Enter PNR number: ");
        String pnr = scanner.nextLine();
        if (reservations.containsKey(pnr)) {
            String passengerName = reservations.get(pnr);
            reservations.remove(pnr);
            System.out.println("Ticket for passenger " + passengerName + " (PNR: " + pnr + ") canceled successfully.");
        } else {
            System.out.println("Invalid PNR number. Please try again.");
        }
    }
}
public class Main {
    public static void main(String[] args) {
        ReservationSystem reservationSystem = new ReservationSystem();
        reservationSystem.start();
    }
}