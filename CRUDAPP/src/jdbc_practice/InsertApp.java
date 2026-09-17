package jdbc_practice;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import Utility_Classes.JdbcUtil;


public class InsertApp {

    private static final String SQL_INSERT_QUERY = "INSERT INTO student (sid, sname, saddress) VALUES (?, ?, ?)";

	public static void main(String[] args) {

  
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        Scanner scanner = new Scanner(System.in);
        int rowCount = 0; 

        try {
            // 1️⃣ Establish database connection
            connection = JdbcUtil.getDbConnection();

            if (connection != null) {
           
               preparedStatement =connection.prepareStatement(SQL_INSERT_QUERY);
            }
            if (preparedStatement!=null && scanner != null) {
				
			
                System.out.print("Enter student ID: ");
                int sid = scanner.nextInt();

                System.out.print("Enter student name: ");
                String sname = scanner.next();

                System.out.print("Enter student address: ");
                String saddress = scanner.next();
                
                preparedStatement.setInt(1, sid);
                preparedStatement.setString(2, sname);
                preparedStatement.setString(3, saddress);

                rowCount = preparedStatement.executeUpdate();
                
                if (rowCount == 0) {
					System.out.println("insersionfail");
				}else {
					System.out.println("no of rescord inserted is "+ rowCount);
				}
            }

        } catch (IOException | SQLException e) {
            System.err.println("Error occurred while inserting record:");
            e.printStackTrace();
        } finally {
    
            scanner.close();
            JdbcUtil.cleanUpResources(null, preparedStatement, connection);
        }
    }
}
