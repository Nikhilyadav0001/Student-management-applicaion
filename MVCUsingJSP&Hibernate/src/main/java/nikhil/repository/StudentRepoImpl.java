package nikhil.repository;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import nikhil.dataobjects.StudentBO;
import nikhil.utility.HibernateUtil;

//orm -> hibernate
public class StudentRepoImpl implements IStudentRepo {
	
	
	
	@Override
	public String insertRecord(StudentBO stdBo) {
		String status = "";
		Transaction trans =null;
		boolean flag =false;
		Session session = HibernateUtil.getSession();
		try {
			trans = session.beginTransaction();
			session.saveOrUpdate(stdBo);
			flag =true;
		} catch (HibernateException he) {
			he.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (trans != null) {
				if (flag) {
					trans.commit();
					status="success";
				}else {
					trans.rollback();
					status="failure";
				}
			}
		}
		return status;
	}

	@Override
	public StudentBO readRecord(Integer sid) {
		Session session = HibernateUtil.getSession();
		StudentBO bo = null;
		try {
			
			//bo=session.load(StudentBO.class, sid);
			//you can use load but in some case looad give errier as it is lasy loading
			bo=session.get(StudentBO.class, sid); 
			
		} catch (HibernateException he) {
			he.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if (session != null) {
				session.close();	
			}
		}
		return bo;
	}

	@Override
	public String deleteRecord(Integer sid) {

		Session session = HibernateUtil.getSession();
		String status = "";
		StudentBO bo = readRecord(sid);
		Transaction trans =null;
		boolean flag =false;
		if (bo != null) {
			//perform delete operation
			try {
				trans = session.beginTransaction();
				session.delete(bo);
				flag =true;
				
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				if (trans != null) {
					if (flag) {
						trans.commit();
						status="success";
					}else {
						trans.rollback();
						status="failure";
					}
				}
				if (session != null) {
					session.close();	
				}
			}
		} else {
			status="Record nn=ot found for::" +sid;
		}
		
		return status;
	}

	@Override
	public String updateRecord(StudentBO stdBo) {
		Session session = HibernateUtil.getSession();
		String status = "";
		Transaction trans =null;
		boolean flag =false;
		try {
			trans = session.beginTransaction();
			//session.merge(stdBo);
			//as we are using the same session saveor update will give error so use merge
			//because of cashing that object is still in the session
			//to fix this create session in each method and close it in each of them
			session.saveOrUpdate(stdBo);
			flag =true;
		} catch (HibernateException he) {
			he.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (trans != null) {
				if (flag) {
					trans.commit();
					status="success";
				}else {
					trans.rollback();
					status="failure";
				}
			}
			if (session != null) {
				session.close();	
			}
		}
		return status;
	}

}
