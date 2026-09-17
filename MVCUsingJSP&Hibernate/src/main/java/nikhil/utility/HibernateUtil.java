package nikhil.utility;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import nikhil.dataobjects.StudentBO;



public class HibernateUtil {
	
	private static SessionFactory sessionFactory=null;
	private static Session session=null;
	
	static {
		if (sessionFactory == null) {
			sessionFactory =new Configuration()
		 					.configure()
		 					.addAnnotatedClass(StudentBO.class)
		 					.buildSessionFactory();
		}
	}
	public static Session getSession() {
		if (sessionFactory != null) {
			return sessionFactory.openSession();
		}
		return session;
	}
	public static void closeSessionFactory() {
		if (sessionFactory != null) {
			sessionFactory.close();
		}
	}
	
	
}
