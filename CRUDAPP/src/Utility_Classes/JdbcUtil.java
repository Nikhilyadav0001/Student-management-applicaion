package Utility_Classes;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class JdbcUtil {

    static Properties p = null;

    static {
        FileInputStream fs = null;
        try {
            fs = new FileInputStream("D:\\java files\\CRUDAPP\\src\\Properties_Files\\database.properties");
            p = new Properties(); // ✅ assign to class-level variable
            p.load(fs);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fs != null)
                    fs.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static Connection getDbConnection() throws IOException, SQLException {
        if (p == null) {
            throw new IOException("Database properties not loaded properly.");
        }

        String url = p.getProperty("url");
        String user = p.getProperty("user");
        String password = p.getProperty("password");

        return DriverManager.getConnection(url, user, password);
    }

    public static void cleanUpResources(ResultSet rs, Statement st, Connection connection) {
        try {
            if (rs != null) rs.close();
            if (st != null) st.close();
            if (connection != null) connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
