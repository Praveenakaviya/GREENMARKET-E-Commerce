package Online_OrganicShop;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class AdminPage {
    private ProductManagement admin;

    public AdminPage() {
        admin = new ProductManagement();
    }

    public void startAdminPanel() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Admin Login");

        boolean exit = false;
        while (!exit) {
            System.out.println("Choose operation:");
            System.out.println("1. Insert Product");
            System.out.println("2. View Products");
            System.out.println("3. Update Product");
            System.out.println("4. Delete Product");
            System.out.println("5. Exit");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    admin.insertProduct();
                    break;
                case 2:
                    admin.viewProducts();
                    break;
                case 3:
                    System.out.println("Enter product ID to update:");
                    int updateId = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    admin.updateProduct(updateId);
                    break;
                case 4:
                    System.out.println("Enter product ID to delete:");
                    int deleteId = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    admin.deleteProduct(deleteId);
                    break;
                case 5:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }
}
