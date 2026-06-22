import java.sql.*;

public class test_db {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/SFP";
        try (Connection conn = DriverManager.getConnection(url, "root", "admin")) {
            DatabaseMetaData meta = conn.getMetaData();
            ResultSet rs = meta.getTables(null, null, "%", new String[]{"TABLE"});
            while (rs.next()) {
                System.out.println("Table: " + rs.getString("TABLE_NAME"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
