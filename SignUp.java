package Online_OrganicShop;
import java.util.Scanner;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SignUp {
    private static Scanner scanner = new Scanner(System.in);
    static void displaySignUpPage() {
        System.out.println("=== Customer Sign Up ===");
        System.out.print("Enter your name: ");
        String name = scanner.nextLine();

        System.out.print("Enter your MailId: ");
        String Email_id = scanner.nextLine();

        System.out.print("Enter your password: ");
        String password = scanner.nextLine();

        System.out.print("Enter your phoneNo: ");
        String phone_no = scanner.nextLine();

//        System.out.print("Enter your role: ");
//        String role = scanner.nextLine();

        if (name.isEmpty() || Email_id.isEmpty() || password.isEmpty() || phone_no.isEmpty()) {
            System.out.println("Username or password cannot be empty. Please try again.");
            displaySignUpPage(); // Allow user to retry sign-up
        } else {
            if (isUsernameAvailable(Email_id)) {
                if (insertCustomer(name, Email_id, password, phone_no)) {
                    System.out.println("Customer created successfully. You can now sign in.");
                } else {
                    System.out.println("Failed to create customer. Please try again.");
                    displaySignUpPage(); // Allow user to retry sign-up
                }
            } else {
                System.out.println("Username already exists. Please choose a different username.");
                displaySignUpPage(); // Allow user to retry sign-up
            }
        }
    }

    private static boolean isUsernameAvailable(String username) {
        Connection conn = DbConnection.getConnection();

        String sql = "SELECT COUNT(*) AS count FROM Customer WHERE Email_id = ?";

            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, username);
                ResultSet rs = stmt.executeQuery();
                if (rs.next()) {
                    int count = rs.getInt("count");
                    return count == 0; // Return true if count is 0 (username is available)
                }

        } catch (SQLException e) {
            System.out.println("Error checking username availability: " + e.getMessage());
        }
        // In case of any exception or error, return false by default
        return false;
    }

    private static boolean insertCustomer(String username, String password, String Email_id, String phone_no) {
        Connection conn = DbConnection.getConnection();
            String sql = "INSERT INTO Customer (name, Email_id, password, phone_no) VALUES (?, ?, ?, ?)";


            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, username);
                stmt.setString(3, Email_id);
                stmt.setString(2, password);
                stmt.setString(4, phone_no);
//                stmt.setString(5, role);
                int rowsInserted = stmt.executeUpdate();
                return rowsInserted > 0;

        } catch (SQLException e) {
            System.out.println("Error inserting customer: " + e.getMessage());
            return false;
        }
    }
}
