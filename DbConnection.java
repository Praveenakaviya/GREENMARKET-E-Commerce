package Online_OrganicShop;
import java.sql.Connection;
import java.sql.DriverManager;


import java.sql.SQLException;

public class DbConnection {
    // Private constructor to prevent instantiation
    private DbConnection() {
    }

    // Singleton instance
    private static Connection connection = null;

    // Static method to get the singleton instance
    public static Connection getConnection() {
        if (connection == null) {
            synchronized (DbConnection.class) {
                if (connection == null) {
                    try {
                        // Correcting the connection string
                        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mysql", "root", "praveenas20@2003");
                        System.out.println("Connection successful ");
                    } catch (SQLException e) {
                        System.out.print("Connection error: " + e);
                    }
                }
            }
        }
        return connection;
    }
}

//public class DbConnection {
//    public static void main(String[] args) {
//        try {
//            // Correcting the connection string
//            Connection obj = DriverManager.getConnection("jdbc:mysql://localhost:3306/mysql", "root", "praveenas20@2003");
//            System.out.println("Connection successful: " + obj);
//        } catch(Exception e) {
//            System.out.print("Connection error: " + e);
//        }
//    }
//}
