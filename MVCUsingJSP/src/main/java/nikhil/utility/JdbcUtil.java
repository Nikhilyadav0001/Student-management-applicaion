package nikhil.utility;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public final class JdbcUtil {
	private static DataSource dataSource = null;

	private JdbcUtil() {

	}

	static {
		FileInputStream fis = null;

		String fileInfo = "D:\\java files\\NikhilServlet\\MVCCRUDAPP\\src\\main\\java\\nikhil\\properties\\hikaricp.properties";

		try {
			HikariConfig config = new HikariConfig(fileInfo);
			dataSource = new HikariDataSource(config);
			System.out.println(dataSource);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (fis != null) {
				try {
					fis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public static Connection getDBConection() throws IOException, SQLException {
		// 1. Establishing the Connection
		return dataSource.getConnection();
	}

	public static void cleanUpResources(ResultSet resultSet, Statement statement, Connection connection) {
		// 5. Close the resources
		if (resultSet != null) {
			try {
				// Closing ResultSet
				resultSet.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		if (statement != null) {
			try {
				// Closing Statement
				statement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (connection != null) {
			try {
				// Closing Connection
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
