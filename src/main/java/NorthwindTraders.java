import java.sql.*;
import java.util.Scanner;


public class NorthwindTraders {
    static String url = "jdbc:mysql://localhost:3306/northwind";
    static String user = "root";
    static String password = "Harrypotter_15";
    static ResultSet rs = null;
    //        Statement stmt = null;

    static Connection conn = null;
    public static void main(String[] args) throws ClassNotFoundException {

        Class.forName("com.mysql.cj.jdbc.Driver");

        System.out.println("What do you want to do?\n" +
                " 1) Display all products\n" +
                " 2) Display all customers\n" +
                " 0) Exit\n" +
                "Select an option: ");
        Scanner scan = new Scanner(System.in);
        String choice = scan.nextLine();
        switch (choice) {
            case "1":
                products();
                break;
            case "2":
                customers();
                break;
            case "0":
                System.exit(0);
            default:
                System.out.println("Invalid option.");
        }
    }
    public static void products() {
        PreparedStatement preparedStatement = null;
        try {
            // Establishing connection
            conn = DriverManager.getConnection(url, user, password);
            //stmt = conn.createStatement();

            preparedStatement = conn.prepareStatement("SELECT * FROM Products");

            // Executing query
            rs = preparedStatement.executeQuery();

            // Processing the result set
            while (rs.next()) {
                System.out.println("\nID: " + rs.getInt("ProductID"));
                System.out.println("Name: " + rs.getString("ProductName"));
                System.out.println("Price: " + rs.getDouble("UnitPrice"));
                System.out.println("Stock: " + rs.getInt("UnitsInStock"));
                System.out.println("-------------------------------------");

            }

            // Closing resources

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // close the resources
            if (rs == null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    public static void customers(){
        PreparedStatement preparedStatement = null;
        try {
            // Establishing connection
            conn = DriverManager.getConnection(url, user, password);
            //stmt = conn.createStatement();

            preparedStatement = conn.prepareStatement("SELECT * FROM Customers ORDER BY Country");
//                    ContactName, CompanyName, City, Country, Phone
            // Executing query
            rs = preparedStatement.executeQuery();

            // Processing the result set
            while (rs.next()) {
                System.out.println("\nContact Name: " + rs.getString("ContactName"));
                System.out.println("Company Name: " + rs.getString("CompanyName"));
                System.out.println("City: " + rs.getString("City"));
                System.out.println("Country: " + rs.getString("Country"));
                System.out.println("Phone: " + rs.getString("Phone"));
                System.out.println("-------------------------------------");

            }

            // Closing resources

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // close the resources
            if (rs == null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}