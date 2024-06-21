package Online_OrganicShop;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class CustomerProfile {
    private Connection connection;
    private Statement statement;
    private Scanner scanner;

    public CustomerProfile() {
        try {
            // Get database connection from DbConnection class
            connection = DbConnection.getConnection();
            statement = connection.createStatement();
            scanner = new Scanner(System.in);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void viewProfile(String email) {
        try {
            String selectSQL = "SELECT * FROM Customer WHERE Email_id = '" + email + "'";
            ResultSet resultSet = statement.executeQuery(selectSQL);
            if (resultSet.next()) {
                int cust_id = resultSet.getInt("cust_id");
                String name = resultSet.getString("name");
                String Email_id = resultSet.getString("Email_id");
                double phone_no = resultSet.getDouble("phone_no");

                System.out.println("ID:          " + cust_id);
                System.out.println("Name:        " + name);
                System.out.println("EmailID:     " + Email_id);
                System.out.println("Phone :      " + phone_no);
                System.out.println();
            } else {
                System.out.println("No profile found for the entered email.");
            }
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        CustomerProfile profile = new CustomerProfile();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter your email: ");
        String email = scanner.nextLine();
        profile.viewProfile(email);
    }
}















//package Online_OrganicShop;
//import java.sql.Connection;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.sql.Statement;
//import java.util.Scanner;
//
//public class CustomerProfile {
//    private Connection connection;
//    private Statement statement;
//    private Scanner scanner;
//
//
//    public  CustomerProfile() {
//        try {
//            // Get database connection from DbConnection class
//            connection = DbConnection.getConnection();
//            statement = connection.createStatement();
//            scanner = new Scanner(System.in);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//    }
//
//
//    public void viewProfile(String email) {
//        try {
//            String selectSQL = "SELECT * FROM Customer";
//            ResultSet resultSet = statement.executeQuery(selectSQL);
//            while (resultSet.next()) {
//                int cust_id = resultSet.getInt("cust_id");
//                String name = resultSet.getString("name");  //cust_id, name, Email_id, password, phone_no
//                String Email_id = resultSet.getString("Email_id");
//                String password = resultSet.getString("password");
//                double phone_no = resultSet.getDouble("phone_no");
//
//                System.out.println("ID:          " + cust_id );
//                System.out.println("Name:        " + name );
//                System.out.println("EmailID:     " + Email_id );
////                System.out.println("password:    " + password);
//                System.out.println("Phone :      " + phone_no);
//                System.out.println();
//
//            }
//            resultSet.close();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//
//
//    }
//
//
//
//}
