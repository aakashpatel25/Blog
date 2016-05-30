package com.Blog.Dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.Blog.Hibernate.HibernateDao;
import com.Blog.Service.ILikes;
import com.Blog.beans.Blog;
import com.Blog.beans.Likes;
import com.Blog.beans.Users;

public class LikesDao implements ILikes {
	private static SessionFactory factory;

	/**
	 * LikesDao constructor.
	 */
	public LikesDao() {
		factory = HibernateDao.openSession();
	}

	@Override
	public boolean setLikes(Blog blog, Users user) {
		Likes like = new Likes(blog, user);
		Session session = factory.openSession();
		Transaction tx = null;
		Long likeId = new Long(0);
		try {
			tx = session.beginTransaction();
			likeId = (Long) session.save(like);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
		} finally {
			session.close();
		}
		return likeId.longValue() > 0;
	}

	@Override
	public long getLikesOnBlog(Blog blog) {
		Session session = factory.openSession();
		String hql = "SELECT count(*) FROM Likes as L where L.blog = :blog";
		long likes = (long) session.createQuery(hql).setParameter("blog", blog).uniqueResult();
		session.close();
		return likes;
	}

	@Override
	public List<Users> getBlogLikesByUser(Blog blog) {
		Session session = factory.openSession();
		String hql = "SELECT user FROM Likes as L where L.blog = :blog";
		@SuppressWarnings("unchecked")
		List<Users> users = session.createQuery(hql).setParameter("blog", blog).list();
		session.close();
		return users;
	}

	@Override
	public boolean unlike(Blog blog, Users user) {
		Session session = factory.openSession();
		String hql = "DELETE FROM Likes WHERE blog = :blog and user= :user";
		int result = session.createQuery(hql).setParameter("blog", blog)
				.setParameter("user", user).executeUpdate();
		session.close();
		return result > 0;
	}
}
