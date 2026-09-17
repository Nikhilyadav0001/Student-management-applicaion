package Factory;

import controller.IStudentController;
import controller.StudentControllerImpl;

public class StudentControllerFactory {
	
	
	private static IStudentController controller = null;

	public static IStudentController getStudentController() {
		if (controller == null) {
			controller =new StudentControllerImpl();
		}
		return controller;
	}


}