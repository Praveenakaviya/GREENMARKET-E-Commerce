package Online_OrganicShop;
import java.sql.Connection;
import java.util.*;
public class MenuPage {
    Connection connection = DbConnection.getConnection();


    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        CustomerProfile Profileobj=new CustomerProfile();
        System.out.println("=================================================================================");
        System.out.println("                             GREENMARKET_ORGANICSHOP                           ");
        System.out.println("=================================================================================");
        System.out.println("1.SIGN UP");
        System.out.println("2.LOG IN");
        System.out.println("3.EXIT");
        System.out.println("=================================================================================");
        int choice = sc.nextInt();
        sc.nextLine(); // Consume newline

        switch (choice) {
            case 1:
                SignUp.displaySignUpPage();
                break;
            case 2:
                System.out.println("Enter your role : ");
                String role = sc.nextLine();
                System.out.println("Enter your password : ");
                String pass = sc.nextLine();
                System.out.println("Enter your mail id : ");
                String email = sc.nextLine();
                SignIn obj = new SignIn();
                boolean isvalid = obj.loginFunction(role, pass, email);
                if (isvalid) {
                    System.out.println("Welcome");

                } else
                    System.out.println("Not valid");
        
                break;
            case 3:
                System.out.println("Exiting......");
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
        }


//        private static void SignUp() {
//            SignUp.displaySignUpPage()();
//        }

    }
}
