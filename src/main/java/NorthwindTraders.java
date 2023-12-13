import org.apache.commons.dbcp2.BasicDataSource;


import java.sql.*;
import java.util.Scanner;


public class NorthwindTraders {
    static String url = "jdbc:mysql://localhost:3306/northwind";
    static String user = "root";
    static String password = "password";


    static BasicDataSource dataSource;
    public static void main(String[] args){

        dataSource = new BasicDataSource();

        dataSource.setUrl(url);
        dataSource.setUsername(user);
        dataSource.setPassword(password);




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
            case "3":
                categories();
                break;
            case "0":
                System.exit(0);
            default:
                System.out.println("Invalid option.");
        }
    }
    public static void products() {
        try (Connection conn = dataSource.getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement("SELECT * FROM Products");
             ResultSet rs = preparedStatement.executeQuery()){

            while (rs.next()) {
                System.out.println("\nID: " + rs.getInt("ProductID"));
                System.out.println("Name: " + rs.getString("ProductName"));
                System.out.println("Price: " + rs.getDouble("UnitPrice"));
                System.out.println("Stock: " + rs.getInt("UnitsInStock"));
                System.out.println("-------------------------------------");

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void customers(){
        try (Connection conn = dataSource.getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement("SELECT * FROM Customers ORDER BY Country");
             ResultSet rs = preparedStatement.executeQuery()
        ){
            while (rs.next()) {
                System.out.println("\nContact Name: " + rs.getString("ContactName"));
                System.out.println("Company Name: " + rs.getString("CompanyName"));
                System.out.println("City: " + rs.getString("City"));
                System.out.println("Country: " + rs.getString("Country"));
                System.out.println("Phone: " + rs.getString("Phone"));
                System.out.println("-------------------------------------");

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void categories(){
        try (Connection conn = dataSource.getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement("SELECT * FROM Categories ORDER BY CategoryID");
             ResultSet rs = preparedStatement.executeQuery()
        ){
            while (rs.next()) {
                System.out.println("\nCategory ID: " + rs.getInt("CategoryID"));
                System.out.println("Category Name: " + rs.getString("CategoryName"));
                System.out.println("-------------------------------------");
            }

            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter a category ID to display products: ");
            int id = scanner.nextInt();

            try(Connection con = dataSource.getConnection();
                PreparedStatement statement = con.prepareStatement("SELECT * FROM Products WHERE CategoryID= ?" )
            ){

                statement.setInt(1,id);

                try(ResultSet r = statement.executeQuery()) {
                    while (r.next()) {
                        System.out.println("\nID: " + r.getInt("ProductID"));
                        System.out.println("Name: " + r.getString("ProductName"));
                        System.out.println("Price: " + r.getDouble("UnitPrice"));
                        System.out.println("Stock: " + r.getInt("UnitsInStock"));
                        System.out.println("-------------------------------------");

                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}