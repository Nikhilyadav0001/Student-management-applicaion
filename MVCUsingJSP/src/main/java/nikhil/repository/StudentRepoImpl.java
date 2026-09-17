package nikhil.repository;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import nikhil.dataobjects.StudentBO;
import nikhil.utility.JdbcUtil;

public class StudentRepoImpl implements IStudentRepo {

	private static Connection connection = null;

	private static PreparedStatement insertPstmt = null;
	private static PreparedStatement selectPstmt = null;
	private static PreparedStatement deletePstmt = null;
	private static PreparedStatement updatePstmt = null;

	private static final String SQL_INSERT_QUERY = "insert into students(`sname`,`sage`,`saddress`,`status`) values(?,?,?,?) ";
	private static final String SQL_SELECT_QUERY = "select sid,sname,sage,saddress from students where sid =? ";
	private static final String SQL_DELETE_QUERY = "delete from students where sid = ? ";
	private static final String SQL_UPDATE_QUERY = "update students set sname=?,sage=?,saddress=?,status=? where sid =?";
	
	static {
		try {
			connection = JdbcUtil.getDBConection();

			if (connection != null)
				insertPstmt = connection.prepareStatement(SQL_INSERT_QUERY);

			if (connection != null)
				selectPstmt = connection.prepareStatement(SQL_SELECT_QUERY);

			if (connection != null)
				deletePstmt = connection.prepareStatement(SQL_DELETE_QUERY);

			if (connection != null)
				updatePstmt = connection.prepareStatement(SQL_UPDATE_QUERY);

		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public String insertRecord(StudentBO stdBo) {
		String status = "";

		try {
			if (insertPstmt != null) {
				insertPstmt.setString(1, stdBo.getSname());
				insertPstmt.setInt(2, stdBo.getSage());
				insertPstmt.setString(3, stdBo.getSaddress());
				insertPstmt.setString(4, stdBo.getStatus());
				System.out.println();
				System.out.println("\n****USING BO TO PERFORM PERISTENCE OPERATION****");
				int rowCount = insertPstmt.executeUpdate();
				if (rowCount == 1) {
					status = "success";
				}
			}
		} catch (SQLException se) {
			int errorCode = se.getErrorCode();
			if (errorCode == 1064) {
				status = "some error in db operation";
			} else if (errorCode == 2006) {
				status = "connection lost at dbside";
			} else if (errorCode == 1045) {
				status = "acess denied for the user wrong credentialsm";
			} else if (errorCode == 1044) {
				status = "access denied for the user to database";
			} else {
				status = "Some Error at database side";
			}
			System.out.println(se);
		} catch (Exception e) {
			status = "Some Unknown Exception ";
			return status;
		}
		return status;
	}

	@Override
	public StudentBO readRecord(Integer sid) {
		StudentBO bo = null;
		try {
			if (selectPstmt != null) {
				selectPstmt.setInt(1, sid);
				ResultSet resultSet = selectPstmt.executeQuery();
				if (resultSet != null) {

					if (resultSet.next()) {
						bo = new StudentBO();
						bo.setSid(resultSet.getInt(1));
						bo.setSname(resultSet.getString(2));
						bo.setSage(resultSet.getInt(3));
						bo.setSaddress(resultSet.getString(4));
						System.out.println(bo);
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return bo;
	}

	@Override
	public String deleteRecord(Integer sid) {

		String status = "";
		StudentBO bo = readRecord(sid);

		if (bo != null) {
			if (deletePstmt != null) {
				try {
					deletePstmt.setInt(1, sid);
					deletePstmt.executeUpdate();
					status = "success";

				} catch (SQLException e) {
					status = "failure";
					e.printStackTrace();
				}
			}

		} else {
			status = "Record not available for the given id :: " + sid;
		}
		return status;
	}

	@Override
	public String updateRecord(StudentBO stdBo) {
		String status = "";
		try {
			if (updatePstmt != null) {
				updatePstmt.setString(1, stdBo.getSname());
				updatePstmt.setInt(2, stdBo.getSage());
				updatePstmt.setString(3, stdBo.getSaddress());
				updatePstmt.setString(4, stdBo.getStatus());

				updatePstmt.setInt(5, stdBo.getSid());

				updatePstmt.executeUpdate();
				status = "success";

			}
		} catch (SQLException e) {
			status = "SOME PROBLEM WITH DATABASE";
			e.printStackTrace();
		}
		return status;
	}

}
