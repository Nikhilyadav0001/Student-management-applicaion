package nikhil.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nikhil.dataobjects.StudentDto;
import nikhil.factory.StudentServiceFactory;
import nikhil.service.IStudentService;

@WebServlet("/controller/*")
public class ControllerService extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request,response);
	}
	
	public void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		IStudentService stdService = StudentServiceFactory.getStudentService();
		
		System.out.println("request uri::" +request.getRequestURI());
		System.out.println("request path info::" +request.getPathInfo());
		//requesting for addform
		if (request.getRequestURI().endsWith("addform")) {
			
			//collecting the input
			String sname = request.getParameter("sname");
			String sage = request.getParameter("sage");
			String saddress = request.getParameter("saddr");
			
			//converting to dto 
			StudentDto dto = new StudentDto();
			dto.setSname(sname);
			dto.setSage(Integer.parseInt(sage));
			dto.setSaddress(saddress);
			
			
			//requesting service later to perform operations
			String status =stdService.insertRecord(dto);
			System.out.println(status);
			RequestDispatcher rd =null;
				
			//redirecting based on results
			if (status.equals("success")) {
			    request.setAttribute("status", "success");
			    rd = request.getRequestDispatcher("/insertResult.jsp");
			    rd.forward(request, response);
			} else {
			    request.setAttribute("status", "failure");
			    rd = request.getRequestDispatcher("/insertResult.jsp");
			    rd.forward(request, response);
			}
		}
		
		
		//requesting for searchform
		if (request.getRequestURI().endsWith("searchform")) {
			String sid = request.getParameter("sid");
			
			StudentDto student = stdService.readRecord(Integer.parseInt(sid));
			
			RequestDispatcher rd =null;
			request.setAttribute("student", student);
			rd =request.getRequestDispatcher("../display.jsp");
			rd.forward(request, response);
			
		}
		
		
		//requesting for delete
		if (request.getRequestURI().endsWith("deleteform")) {
		
			String sid = request.getParameter("sid");
			String status = stdService.deleteRecord(Integer.parseInt(sid));
			
			System.out.println(status);
			
			RequestDispatcher rd =null;
			
			//redirecting based on results
			if (status.equals("success")) {
				request.setAttribute("status", "success");
				rd =request.getRequestDispatcher("../deleteResult.jsp");
				rd.forward(request, response);
			} else if(status.equals("failure")){
				request.setAttribute("status", "failure");
				rd =request.getRequestDispatcher("../deleteResult.jsp");
				rd.forward(request, response);	
			}else {
				request.setAttribute("status", "not found");
				rd =request.getRequestDispatcher("../deleteResult.jsp");
				rd.forward(request, response);
			}
		}
		//request for edit form
		if (request.getRequestURI().endsWith("editform")) {
			
			String sid =request.getParameter("sid");
			StudentDto student =stdService.readRecord(Integer.parseInt(sid));
			
			//redirect to jsp page
			RequestDispatcher rd =null;
			
			if (student != null) {
				request.setAttribute("student", student);
				rd= request.getRequestDispatcher("../updateForm.jsp");
				rd.forward(request, response);
			}
			
		}
		
		//requesting for update form
		
		if (request.getRequestURI().endsWith("updateRecord")) {
			//Collect the inputs from UI Page
			String sid = request.getParameter("sid");
			String sname = request.getParameter("sname");
			String sage = request.getParameter("sage");
			String saddress = request.getParameter("saddress");
			
			
			//Store the input to DTO
			StudentDto dto = new StudentDto();
			dto.setSid(Integer.parseInt(sid));
			dto.setSname(sname);
			dto.setSaddress(saddress);
			dto.setSage(Integer.parseInt(sage));
			
			//Calling the service to perform update operation
			String status = stdService.updateRecord(dto);
			System.out.println(status);

			RequestDispatcher rd = null;
			// Redirecting to suitable pages based on the result
			request.setAttribute("status", status);
		    rd = request.getRequestDispatcher("/updateResult.jsp");
		    rd.forward(request, response);
		}
	}
	
}