package jdbc_practice;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import Factory.StudentControllerFactory;
import controller.IStudentController;
import dto.Student;

public class MainApp {
	public static void main(String[] args) {
		
		IStudentController controller = StudentControllerFactory.getStudentController();
		
		Student student = new Student();
		
		Scanner scanner = new Scanner(System.in);
		System.out.println("choose c for insert \n r for dispaly all  \n u for update \n d for delet");
		String chose= scanner.nextLine();
		
		switch(chose) {
		
		case "c":{
		 System.out.print("Enter student ID: ");
         int sid = scanner.nextInt();

         System.out.print("Enter student name: ");
         String sname = scanner.next();

         System.out.print("Enter student address: ");
         String saddress = scanner.next();
         
		student.setSid(sid);
		student.setSname(sname);
		student.setSaddress(saddress);
		
		
		try {
			System.out.println("no of record inserted is::"+controller.insertRecord(student));
		} catch (SQLException e) {
			System.out.println("insersion falure");
		}}
		break;

		case "r":{
			try {
				ArrayList<Student> a=controller.displayRecord();
				a.forEach(System.out::println);
			} catch (SQLException e) {
			System.out.println("aasi tesi");
				e.printStackTrace();
			}
			
		}
		break;
		case"u":{
			 System.out.print("Enter student ID: ");
	         int sid = scanner.nextInt();

	         System.out.print("Enter student you want name: ");
	         String sname = scanner.next();

	         System.out.print("Enter student you want address: ");
	         String saddress = scanner.next();
	         
			student.setSid(sid);
			student.setSname(sname);
			student.setSaddress(saddress);
			
			
			try {
				System.out.println("no of record updated is::"+controller.updateRecord(student));
			} catch (SQLException e) {
				System.out.println("update falure");
				e.printStackTrace();
			}}
		break;
		case"d":{
			 System.out.print("Enter student ID: ");
	         int sid = scanner.nextInt();

			student.setSid(sid);
			
			try {
				System.out.println("no of record deleted is::"+controller.deleteRecord(student));
			} catch (SQLException e) {
				System.out.println("delete falure");
			}}
		break;
		default:
			System.out.println("na mana");
			
			
		}
		
	}
}
