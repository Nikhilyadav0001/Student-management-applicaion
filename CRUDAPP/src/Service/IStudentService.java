package Service;

import java.sql.SQLException;
import java.util.ArrayList;

import dto.Student;

public interface IStudentService {
	public int insertRecord(Student student) throws SQLException;
	public int updateRecord(Student student) throws SQLException;
	public int deleteRecord(Student student) throws SQLException;
	public ArrayList<Student> displayRecord() throws SQLException;
}
