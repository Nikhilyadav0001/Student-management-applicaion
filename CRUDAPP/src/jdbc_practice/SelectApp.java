package jdbc_practice;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import Utility_Classes.JdbcUtil;

public final class SelectApp {

    public static void main(String[] args) {
        Connection connection = null;
        Statement st = null;
        ResultSet rs = null;

        try {
            // Get database connection from DbUtil
            connection = JdbcUtil.getDbConnection();

            // Create statement object
            st = connection.createStatement();
            System.out.println("Statement class: " + st.getClass().getName());

            // Execute query
            String sqlQuery = "SELECT sid, sname, saddress FROM student";
            rs = st.executeQuery(sqlQuery);

            // Process the result
            System.out.println("SID\tName\tAddress");
            System.out.println("--------------------------");

            while (rs.next()) {
                int sid = rs.getInt("sid");
                String sname = rs.getString("sname");
                String saddress = rs.getString("saddress");
                System.out.println(sid + "\t" + sname + "\t" + saddress);
            }

        } catch (IOException | SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcUtil.cleanUpResources(rs, st, connection);
        }
    }
}
