package jdbc_practice;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import Utility_Classes.JdbcUtil;

public class DeleteApp {

    public static void main(String[] args) {
        Connection connection = null;
        Statement statement = null;
        int rowCount = 0;
        Scanner sc = new Scanner(System.in);

        try {
            connection = JdbcUtil.getDbConnection();

            if (connection != null) {
                statement = connection.createStatement();
            }

            System.out.print("Enter student id to delete: ");
            int sid = sc.nextInt();

            if (statement != null) {
                // ✅ SQL delete query
                String sqlDeleteQuery = "DELETE FROM student WHERE sid = " + sid;
                rowCount = statement.executeUpdate(sqlDeleteQuery);
            }

            if (rowCount == 0) {
                System.out.println("No record found with ID " + sid);
            } else {
                System.out.println("Record deleted successfully.");
            }

        } catch (IOException | SQLException e) {
            e.printStackTrace();
        } finally {
            sc.close();
            JdbcUtil.cleanUpResources(null, statement, connection);
        }
    }
}
