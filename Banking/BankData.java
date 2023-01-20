import java.sql.*;
import java.util.*;

/*
     * The steps in JDBC
     * Import the package(import java.sql.*)
     * Load the Driver..Register the Driver
     * Create a connection
     * Create the statement
     * Execute the statement
     * Process the result
     * Close the connection
     * jdbc:mysql://localhost:3306/myDatabase
     * com.mysql.cj.jdbc.Driver
     */

public class BankData {

    public static void verifyUser() throws Exception {
        Scanner scan = new Scanner(System.in);

        while (true) {
            try {
                System.out.print("Please Enter Your Account Number:");
                int accNumber = Integer.parseInt(scan.nextLine());
                System.out.print("Please Enter your PIN: ");
                int pin = Integer.parseInt(scan.nextLine());

                String url = "jdbc:mysql://localhost:3306/myDatabase";
                String username = "root";
                String pass = "katotrevormine";

                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection con = DriverManager.getConnection(url, username, pass);
                PreparedStatement st = con
                        .prepareStatement("SELECT * from BankUsers where AccountNumber = ? and Pin = ?");
                st.setInt(1, accNumber);
                st.setInt(2, pin);

                ResultSet rs = st.executeQuery();

                if (rs.next()) {
                    System.out.println("Successful Login\n");
                    st.close();
                    con.close();
                    break;
                } else {
                    System.out.println("Wrong credentials\n");
                    st.close();
                    con.close();
                    continue;
                }
            } catch (SQLException e) {
                System.out.println("Operation failed!!");

            } catch (NumberFormatException nfe) {
                System.out.println("Please fill in digits!!");

            }
        }
    }

    public static void getDetails() throws Exception {
        while (true) {
            try {

                Scanner scan = new Scanner(System.in);
                System.out.print("Enter Account Number: ");
                int bankAccount = Integer.parseInt(scan.nextLine());

                BankUser user = new BankUser();

                String url = "jdbc:mysql://localhost:3306/myDatabase";
                String username = "root";
                String pass = "katotrevormine";

                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection con = DriverManager.getConnection(url, username, pass);

                PreparedStatement st = con.prepareStatement("SELECT * FROM BankUsers WHERE AccountNumber = ?");
                st.setInt(1, bankAccount);
                ResultSet rs = st.executeQuery();
                rs.next();
                user.name = rs.getString(2);
                user.balance = rs.getInt(4);

                System.out.println("User name: " + user.name + "\nAccount Balance: " + user.balance);

                scan.close();
                st.close();
                con.close();
                break;
            } catch (SQLException e) {
                System.out.println("Operation failed!!");
                break;
            } catch (NumberFormatException nfe) {
                System.out.println("Please fill in digits!!");
                continue;
            }
        }

    }

    public static void depositMoney() throws Exception {
        while (true) {
            try {
                Scanner scan = new Scanner(System.in);
                System.out.print("Enter Account Number: ");
                int bankAccount = Integer.parseInt(scan.nextLine());
                System.out.print("Enter Amount you want to deposit: ");
                int amount = Integer.parseInt(scan.nextLine());

                String url = "jdbc:mysql://localhost:3306/myDatabase";
                String username = "root";
                String pass = "katotrevormine";

                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection con = DriverManager.getConnection(url, username, pass);

                PreparedStatement st = con.prepareStatement(
                        "UPDATE BankUsers SET AccountBalance = AccountBalance + ? WHERE AccountNumber = ?");
                st.setInt(1, amount);
                st.setInt(2, bankAccount);

                st.executeUpdate();

                System.out.println("Operation Successful!");

                st.close();
                con.close();
                scan.close();
                break;
            } catch (SQLException e) {
                System.out.println("Operation Failed");
                break;
            } catch (NumberFormatException nfe) {
                System.out.println("Please fill In digits");
                continue;
            }
        }
    }

    public static void withdrawMoney() throws Exception {
        while (true) {
            try {
                Scanner scan = new Scanner(System.in);
                System.out.print("Enter Account Number: ");
                int bankAccount = Integer.parseInt(scan.nextLine());
                System.out.print("Enter Amount you want to withdraw: ");
                int amount = Integer.parseInt(scan.nextLine());

                String url = "jdbc:mysql://localhost:3306/myDatabase";
                String username = "root";
                String pass = "katotrevormine";

                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection con = DriverManager.getConnection(url, username, pass);

                PreparedStatement st = con.prepareStatement(
                        "UPDATE BankUsers SET AccountBalance = AccountBalance - ? WHERE AccountNumber = ?");
                st.setInt(1, amount);
                st.setInt(2, bankAccount);

                st.executeUpdate();

                System.out.println("Operation Successful!");

                st.close();
                con.close();
                scan.close();
                break;
            } catch (SQLException e) {
                System.out.println("Operation Failed");
                break;
            } catch (NumberFormatException nfe) {
                System.out.println("Please fill In digits");
                continue;
            }
        }
    }

}
