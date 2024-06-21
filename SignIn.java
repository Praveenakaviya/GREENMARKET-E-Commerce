package Online_OrganicShop;
import com.mysql.cj.x.protobuf.MysqlxCrud;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class SignIn {
    Connection connection = DbConnection.getConnection();
    Scanner sc=new Scanner(System.in);
    Connection con = DbConnection.getConnection();
     AdminPage myobj=new AdminPage();
     Productspage ProductObj=new Productspage();

     Order OrderObj=new Order();
     CustomerProfile Profileobj=new CustomerProfile();
     MenuPage mp=new MenuPage();

    public boolean loginFunction(String role, String pass, String email) {
        Connection connection = DbConnection.getConnection();
        if (role.equalsIgnoreCase("ADMIN")) {
            String query = "SELECT * FROM admin ";
            try (PreparedStatement prep = con.prepareStatement(query)) {
                ResultSet rs = prep.executeQuery();
                while (rs.next()) {
                    String adminpass = rs.getString("password");
                    if (adminpass.equals(pass)) {
                        System.out.println("Login success ");
                        myobj.startAdminPanel();
                        return true;
                    }
                }
                return false;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } else if (role.equalsIgnoreCase("CUSTOMER")) {
            String query = "SELECT * FROM Customer WHERE password = ? and Email_id=?";
            try (PreparedStatement prep = con.prepareStatement(query)) {
                prep.setString(1, pass);
                prep.setString(2, email);
                ResultSet rs = prep.executeQuery();
                if (rs.next()) {
                    boolean exit = false;
                    while (!exit) {
                        System.out.println("1.ViewProducts");
                        System.out.println("2.OrderThings");
                        System.out.println("3.View Your Profile");
                        int h=sc.nextInt();
                        switch (h) {
                            case 1:
                                ProductObj.viewProducts();

                                break;
                            case 2:
                                OrderObj.processUserOrder(connection);
                                 break;

                            case 3:
                                System.out.print("Enter your EmailID:");
                                String Email=sc.next();
                                if(Objects.equals(email, Email)){
                                    Profileobj.viewProfile(Email);
                                }
                                else{
                                    System.out.println("Ivalid");
                                }
                                break;

                            case 4:
                                System.out.print("Invalid");
                        }
                    }
                    return true;
                }

                return false;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } else {
            System.out.println("Invalid password try signing in");
        }
        return false;
    }


    public int getCustomerId(Connection connection, String email) {
        System.out.println("Enter your cust_Id");
        int cust_id=sc.nextInt();
        return cust_id;

    }
}
