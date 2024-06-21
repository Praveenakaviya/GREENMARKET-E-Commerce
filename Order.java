package Online_OrganicShop;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Order {
    private int customerId;
    private int productId;
    private int quantity;
    private double totalPrice;

    public Order() {
        // Default constructor
    }

    public Order(int customerId, int productId, int quantity) {
        this.customerId = customerId;
        this.productId = productId;
        this.quantity = quantity;
    }

    // Method to process the order, get order details from user, and calculate total price
    public void processUserOrder(Connection connection) {
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.println("Enter your email:");
            String email = scanner.nextLine();
            SignIn signIn = new SignIn();
            int customerId = signIn.getCustomerId(connection, email);
            if (customerId != -1) {
                System.out.println("Enter Product ID:");
                int productId = scanner.nextInt();
                System.out.println("Enter Quantity:");
                int quantity = scanner.nextInt();

                // Query to get product details and deduct quantity
                String getProductQuery = "SELECT name, price, Stocks FROM Products WHERE product_id = ?";
                String updateProductQuery = "UPDATE Products SET Stocks = ? WHERE product_id = ?";
                String insertOrderQuery = "INSERT INTO orders (cust_id, product_id, total_amount, status) VALUES (?, ?, ?, ?)";

                // Retrieve product details
                PreparedStatement getProductStatement = connection.prepareStatement(getProductQuery);
                getProductStatement.setInt(1, productId);
                ResultSet resultSet = getProductStatement.executeQuery();

                if (resultSet.next()) {
                    String productName = resultSet.getString("name");
                    double productPrice = resultSet.getDouble("price");
                    int stocks = resultSet.getInt("Stocks");

                    // Check if quantity is sufficient
                    if (stocks >= quantity) {
                        // Deduct quantity from stock
                        int updatedQuantity = stocks - quantity;
                        PreparedStatement updateProductStatement = connection.prepareStatement(updateProductQuery);
                        updateProductStatement.setInt(1, updatedQuantity);
                        updateProductStatement.setInt(2, productId);
                        updateProductStatement.executeUpdate();

                        // Calculate total price
                        totalPrice = productPrice * quantity;

                        // Insert order details into orders table
                        PreparedStatement insertOrderStatement = connection.prepareStatement(insertOrderQuery);
                        insertOrderStatement.setInt(1, customerId);
                        insertOrderStatement.setInt(2, productId);
                        insertOrderStatement.setDouble(3, totalPrice);
                        insertOrderStatement.setString(4, "Pending"); // Assuming status is initially set to "Pending"
                        insertOrderStatement.executeUpdate();

                        // Print order details
                        System.out.println("Order placed successfully:");
                        System.out.println("Product Name: " + productName);
                        System.out.println("Quantity: " + quantity);
                        System.out.println("Total Price: " + totalPrice);
                    } else {
                        System.out.println("Insufficient quantity available for product with ID: " + productId);
                    }
                } else {
                    System.out.println("Product with ID: " + productId + " not found.");
                }
            } else {
                System.out.println("Invalid email or customer not found.");
            }
        } catch (SQLException e) {
            System.out.println("Error processing order: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        // Assuming you have a database connection object named "con"
        Connection con = DbConnection.getConnection();

        // Process the order
        Order order = new Order();
        order.processUserOrder(con);
    }
}





