package controller;

import java.sql.SQLException;
import java.util.ArrayList;

import Factory.StudentServiceFactory;
import Service.IStudentService;
import dto.Student;

public class StudentControllerImpl implements IStudentController{

	@Override
	public int insertRecord(Student student) throws SQLException {

		IStudentService service = StudentServiceFactory.getStudentService();
		return service.insertRecord(student);
		
	}

	@Override
	public int updateRecord(Student student) throws SQLException {
		IStudentService service = StudentServiceFactory.getStudentService();
		return service.updateRecord(student);
	}

	@Override
	public int deleteRecord(Student student) throws SQLException {
		IStudentService service = StudentServiceFactory.getStudentService();
		return service.deleteRecord(student);
	}

	@Override
	public ArrayList<Student> displayRecord() throws SQLException {
		IStudentService service = StudentServiceFactory.getStudentService();
		return service.displayRecord();
		
	}
	
}
