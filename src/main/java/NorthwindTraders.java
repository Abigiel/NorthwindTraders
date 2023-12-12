import java.sql.*;
import javax.sql.DataSource;

public class NorthwindTraders {
    public static void main(String[] args) throws ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");


        String url = "jdbc:mysql://localhost:3306/northwind";
        String user = "username";
        String password = "password";

        String query = "SELECT * FROM Products";
        try {
            // Establishing connection
            Connection conn = DriverManager.getConnection(url, user, password);
            Statement stmt = conn.createStatement();

            // Executing query
            ResultSet rs = stmt.executeQuery(query);

            // Processing the result set
            while (rs.next()) {
                System.out.println("\nID: " + rs.getInt("ProductID"));
                System.out.println("Name: " + rs.getString("ProductName"));
                System.out.println("Price: " + rs.getDouble("UnitPrice"));
                System.out.println("Stock: " + rs.getInt("UnitsInStock"));
                System.out.println("-------------------------------------");

            }

            // Closing resources
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}