package Factory;

import Repository.IStudentRepo;
import Repository.StudentRepoImpl;

public class StudentRepoFactory {
	
	private static StudentRepoImpl studentRepo = null;
	private StudentRepoFactory() {
		
	}
	
	public static IStudentRepo getStudentRepo() {
		if (studentRepo == null) {
			studentRepo = new StudentRepoImpl();
		}
		return studentRepo;
	}
}
