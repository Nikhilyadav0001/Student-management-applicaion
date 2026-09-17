package Repository;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import Utility_Classes.JdbcUtil;
import dto.Student;

public class StudentRepoImpl implements IStudentRepo {
	
	private static Connection connection = null;
	
	
	static {
		try {
			connection =JdbcUtil.getDbConnection();
		} catch (IOException | SQLException e) {
			e.printStackTrace();
		}
		
	}
	@Override
	public int insertRecord(Student student) throws SQLException {
		Statement statement = null;
		if(connection!=null)
		statement = connection.createStatement();
		
		
		String sqlInsertQuery = null;
		sqlInsertQuery=String.format("insert into student values(%d,'%s','%s')",student.getSid(),
				student.getSname(),student.getSaddress());
		System.out.println(sqlInsertQuery);
		
		if (statement != null&& sqlInsertQuery != null ) {
			return statement.executeUpdate(sqlInsertQuery);
		}
		return 0;
	}
	
	@Override
	public ArrayList<Student> displayRecord() throws SQLException {

	    ArrayList<Student> studList = new ArrayList<>();

	    if (connection != null) {
	        Statement statement = connection.createStatement();
	        String sql = "SELECT * FROM student";

	        ResultSet rs = statement.executeQuery(sql);

	        while (rs.next()) {
	            int sid = rs.getInt("sid");  // ✅ column name or index 1
	            String sname = rs.getString("sname"); // ✅ index 2
	            String saddress = rs.getString("saddress"); // ✅ index 3

	            Student stud = new Student();  // ✅ create new object inside loop
	            stud.setSid(sid);
	            stud.setSname(sname);
	            stud.setSaddress(saddress);

	            studList.add(stud);
	        }
	    }

	    return studList;
	}

	@Override
	public int updateRecord(Student student) throws SQLException{
		PreparedStatement statement = null;
		String SQLQ = "UPDATE student SET sname = ?, saddress = ? WHERE sid = ?";
		if(connection!=null)
		statement = connection.prepareStatement(SQLQ);
		statement.setString(1,student.getSname());
		statement.setString(2,student.getSaddress());
		statement.setInt(3,student.getSid());
		System.out.println(SQLQ);
		
		if (statement != null&& SQLQ != null ) {
			return statement.executeUpdate();
		}
		return 0;
	}
	@Override
	public int deleteRecord(Student student) throws SQLException {
		PreparedStatement statement = null;
		String SQLQ ="DELETE FROM student WHERE sid = ?";
		if(connection!=null)
		statement = connection.prepareStatement(SQLQ);
		statement.setInt(1,student.getSid());
		
		System.out.println(SQLQ);
		
		if (statement != null&& SQLQ != null ) {
			return statement.executeUpdate();
		}
		return 0;
	}
 
}
