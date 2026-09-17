package Factory;

import Service.IStudentService;
import Service.StudentServiceImpl;

public class StudentServiceFactory {

	private static IStudentService studentservice =null;
	
	private StudentServiceFactory() {
		
	}
	public static IStudentService getStudentService() {
		if (studentservice == null) {
			studentservice = new StudentServiceImpl();
		}
		return studentservice;
	}
}
