package nikhil.factory;

import nikhil.repository.IStudentRepo;
import nikhil.repository.StudentRepoImpl;

public class StudentRepoFactory {

	private static StudentRepoImpl studentRepo = null;

	private StudentRepoFactory() {

	}

	// Singleton pattern
	public static IStudentRepo getStudentRepo() {
		if (studentRepo == null) {
			studentRepo = new StudentRepoImpl();
		}
		return studentRepo;
	}
}
