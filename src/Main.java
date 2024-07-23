import java.sql.*;

public class Main {
    public static void main(String[] args) {
        String jdbcUrl = "jdbc:mysql://localhost:3306/db_hr";
        String username = "root";
        String password = "Dewangga19!";

        try {
            // Register JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Open a connection
            Connection conn = DriverManager.getConnection(jdbcUrl, username, password);
            
            // Do something with the connection here (e.g., execute SQL queries)
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM countries");

            System.out.format("%-3s | %-25s | %-30s | %-25s\n", "Id", "Country Name", "Region id", "Population" );

            while (rs.next()) {
                // Retrieve data from the result set
                int id = rs.getInt("country_id");
                String name = rs.getString("country_name");
                int regionId = rs.getInt("region_id");
                int population = rs.getInt("population");

                // Example: Print retrieved data
                System.out.format("%-3s | %-25s | %-30s | %-25s\n", id, name, regionId, population);
            }

            // Close the connection
            conn.close();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
