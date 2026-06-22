import java.sql.Connection;
import java.sql.DriverManager;
public class TestDB {
    public static void main(String[] args) {
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/SFP", "root", "admin");
            java.sql.ResultSet rs = conn.createStatement().executeQuery("SHOW TABLES");
            while(rs.next()) System.out.println(rs.getString(1));
        } catch(Exception e) { e.printStackTrace(); }
    }
}
