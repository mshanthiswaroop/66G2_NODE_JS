import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class DatabaseConnection {
 Connection con;
public DatabaseConnection() {
 try {
 Class.forName("com.mysql.cj.jdbc.Driver");
 con = DriverManager.getConnection(
 "jdbc:mysql://localhost:3306/cmrtclab",
 "root",
 "root");
 System.out.println("Database Connected Successfully");
 } catch (ClassNotFoundException | SQLException e) {
 e.printStackTrace();
 }
}
} 
