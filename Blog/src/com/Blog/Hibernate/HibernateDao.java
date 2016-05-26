package com.Blog.Hibernate;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateDao {
	private static SessionFactory factory; 
	
	private HibernateDao(){
		try{
			factory = new Configuration().configure("/com/Blog/Hibernate/hibernate.cfg.xml").buildSessionFactory();
		}catch (Throwable ex){
			System.err.println("Failed to create sessionFactory object." + ex);
	        throw new ExceptionInInitializerError(ex); 
		}
	}
	
	public static SessionFactory openSession(){
		if(factory==null){
			new HibernateDao();
		}
		factory.openSession();
		return factory;
	}
}
