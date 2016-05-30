package com.Blog.Dao;

import java.util.Date;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.Blog.Hibernate.HibernateDao;
import com.Blog.Service.IUsers;
import com.Blog.beans.Users;

public class UsersDao implements IUsers {
	public static SessionFactory factory;

	/**
	 * UserDao constructor.
	 */
	public UsersDao() {
		factory = HibernateDao.openSession();
	}

	@Override
	public boolean createNewUser(Users user) {
		Session session = factory.openSession();
		Transaction tx = null;
		Long id = new Long(0);
		try {
			Date time = new Date();
			tx = session.beginTransaction();
			user.setUserSince(time);
			id = (Long) session.save(user);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
		} finally {
			session.close();
		}
		return id.longValue() > 0;
	}

	@Override
	public boolean changePassword(long userId, String newPassword) {
		Session session = factory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			Users user = (Users) session.get(Users.class, userId);
			user.setPassword(newPassword);
			session.update(user);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
		} finally {
			session.close();
		}
		return false;
	}

	@Override
	public boolean changeEmail(long userId, String newEmail) {
		Session session = factory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			Users user = (Users) session.get(Users.class, userId);
			user.setEmail(newEmail);
			session.update(user);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
		} finally {
			session.close();
		}
		return true;
	}

	@Override
	public boolean updateFirstAndLastName(long userId, String fName, String lName) {
		Session session = factory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			Users user = (Users) session.get(Users.class, userId);
			user.setFirstName(fName);
			user.setLastName(lName);
			session.update(user);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
		}
		return true;
	}

	@Override
	public long findUserId(String userName) {
		try {
			Session session = factory.openSession();
			Long userId = (Long) session
					.createQuery("Select u.userId from Users as u where u.username = :userName")
					.setParameter("userName", userName).uniqueResult();
			session.close();
			return userId.longValue();
		} catch (NullPointerException e) {
			return 0;
		}
	}

	@Override
	public boolean deleteUser(long userId) {
		Session session = factory.openSession();
		int result = session.createQuery("Delete Users where userId = :userId")
				.setParameter("userId", userId).executeUpdate();
		session.close();
		return result > 0;
	}

	@Override
	public Users getUser(String userName) {
		Session session = factory.openSession();
		Users user = (Users) session
				.createQuery("From Users where username = :username")
				.setParameter("username", userName).uniqueResult();
		return user;
	}
}
