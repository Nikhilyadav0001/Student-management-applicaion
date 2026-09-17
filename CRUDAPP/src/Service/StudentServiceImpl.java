package Service;

import java.sql.SQLException;
import java.util.ArrayList;

import Factory.StudentRepoFactory;
import Repository.IStudentRepo;
import Repository.StudentRepoImpl;
import dto.Student;

public class StudentServiceImpl implements IStudentService {
	
	@Override
	public int insertRecord(Student student) throws SQLException {
		IStudentRepo repo = StudentRepoFactory.getStudentRepo();
		return repo.insertRecord(student);
	}

	@Override
	public int updateRecord(Student student) throws SQLException {
		IStudentRepo repo = StudentRepoFactory.getStudentRepo();
		return repo.updateRecord(student);
	}

	@Override
	public int deleteRecord(Student student) throws SQLException {
		IStudentRepo repo = StudentRepoFactory.getStudentRepo();
		return repo.deleteRecord(student);
	}

	@Override
	public ArrayList<Student> displayRecord() throws SQLException {
		IStudentRepo repo = StudentRepoFactory.getStudentRepo();
		return repo.displayRecord();
	}

}
