package Online_OrganicShop;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
public class ProductManagement {
    private Connection connection;
    private Statement statement;
    private Scanner scanner;

    public ProductManagement() {
        try {
            connection = DbConnection.getConnection();
            statement = connection.createStatement();
            scanner = new Scanner(System.in);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertProduct() {
        try {
            System.out.println("Enter product category:");
            String category = scanner.nextLine();

            System.out.println("Enter product name:");
            String name = scanner.nextLine();

            System.out.println("Enter product price:");
            double price = scanner.nextDouble();

            System.out.println("Enter product Stocks:");
            int stocks = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            String insertSQL = "INSERT INTO products (category, name, price, Stocks) VALUES ('" + category + "', '" + name + "', '" + price + "', " + stocks + ")";


            int rowsAffected = statement.executeUpdate(insertSQL);
            if (rowsAffected > 0) {
                System.out.println("Product inserted successfully.");
            } else {
                System.out.println("Failed to insert product.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void viewProducts() {
        try {
            String selectSQL = "SELECT * FROM products";
            ResultSet resultSet = statement.executeQuery(selectSQL);
            while (resultSet.next()) {
                int id = resultSet.getInt("product_id");
                String category = resultSet.getString("category");
                String name = resultSet.getString("name");
                double price = resultSet.getDouble("price");
                String Stocks = resultSet.getString("Stocks");
                System.out.println();
                System.out.println("ID:          " + id );
                System.out.println("Category:    " + category );
                System.out.println("Name:        " + name );
                System.out.println("Price:       " + price );
                System.out.println("Stocks:       "+ Stocks);
                System.out.println();
            }
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateProduct(int productId) {
        try {
            System.out.println("Enter product category:");
            String category = scanner.nextLine();

            System.out.println("Enter product name:");
            String name = scanner.nextLine();

            System.out.println("Enter product price:");
            double price = scanner.nextDouble();

            System.out.println("Enter product Stocks:");
            String Stocks = scanner.nextLine();

            String updateSQL = "UPDATE products SET category = '" + category + "', name = '" + name +
                    "', price = '" + price + "', Stocks = " + Stocks + " WHERE product_id = " + productId;
            int rowsAffected = statement.executeUpdate(updateSQL);
            if (rowsAffected > 0) {
                System.out.println("Product updated successfully.");
            } else {
                System.out.println("No product found with the given ID.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteProduct(int productId) {
        try {
            String deleteSQL = "DELETE FROM products WHERE product_id = " + productId;
            int rowsAffected = statement.executeUpdate(deleteSQL);
            if (rowsAffected > 0) {
                System.out.println("Product deleted successfully.");
            } else {
                System.out.println("No product found with the given ID.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void closeConnection() {
        try {
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
            if (scanner != null) {
                scanner.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
