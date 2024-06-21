package Online_OrganicShop;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import java.util.*;
public class Productspage {
    private Connection connection;
    private Statement statement;
    private Scanner scanner;


    public Productspage() {
        try {
            // Get database connection from DbConnection class
            connection = DbConnection.getConnection();
            statement = connection.createStatement();
            scanner = new Scanner(System.in);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


    public void viewProducts() {
        try {
            String selectSQL = "SELECT * FROM Products";
            ResultSet resultSet = statement.executeQuery(selectSQL);
            while (resultSet.next()) {
                int product_id = resultSet.getInt("product_id");
                String category = resultSet.getString("category");
                String name = resultSet.getString("name");
                double price = resultSet.getDouble("price");
                String Stocks = resultSet.getString("Stocks");

                System.out.println("ID:          " + product_id );
                System.out.println("Category:    " + category );
                System.out.println("Name:        " + name );
                System.out.println("Price:       " + price);
                System.out.println("Stocks:      " +Stocks);
                System.out.println();

            }
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }



    }

    public void OrderPage() {
    }
}
