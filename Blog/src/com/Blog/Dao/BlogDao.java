package com.Blog.Dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.Blog.Hibernate.HibernateDao;
import com.Blog.Service.IBlog;
import com.Blog.beans.Blog;
import com.Blog.beans.Users;

public class BlogDao implements IBlog {
	private static SessionFactory factory;

	/**
	 * Constructor of the BlogDao.
	 */
	public BlogDao() {
		factory = HibernateDao.openSession();
	}

	@Override
	public boolean insertBlog(Blog blog) {
		Session session = factory.openSession();
		Transaction tx = null;
		Long blogId = new Long(0);
		try {
			tx = session.beginTransaction();
			blogId = (Long) session.save(blog);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
		} finally {
			session.close();
		}
		return blogId.longValue() > 0;
	}

	@Override
	public boolean editTitle(String newTitle, long blogId) {
		Session session = factory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			Blog blog = (Blog) session.get(Blog.class, blogId);
			blog.setTitle(newTitle);
			session.update(blog);
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
	public boolean editPost(String newPost, long blogId) {
		Session session = factory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			Blog blog = (Blog) session.get(Blog.class, blogId);
			blog.setPost(newPost);
			session.update(blog);
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
	public Blog getBlog(long blogId) {
		Session session = factory.openSession();
		String hql = "from Blog as b where b.id = :blogId";
		Query query = session.createQuery(hql).setParameter("blogId", blogId);
		Blog blog = (Blog) query.uniqueResult();
		return blog;
	}

	@Override
	public boolean deleteBlog(long blogId) {
		Session session = factory.openSession();
		int result = session.createQuery("Delete Blog where id = :blogId")
				.setParameter("blogId", blogId).executeUpdate();
		session.close();
		return result > 0;
	}

	@Override
	public long getBlogId(Users user, String title) {
		try {
			Session session = factory.openSession();
			Long result = (Long) session
					.createQuery("Select b.id from Blog as b where b.user = :user and b.title = :title")
					.setParameter("user", user)
					.setParameter("title", title).uniqueResult();
			session.close();
			return result.longValue();
		} catch (NullPointerException e) {
			return 0;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Blog> fetchFiveRecentBlogs() {
		Session session = factory.openSession();
		Long maxId = (Long) session.createQuery("Select max(b.id) from Blog as b").uniqueResult();
		Transaction tx = null;
		List<Blog> blogList = null;
		try {
			tx = session.beginTransaction();
			Criteria criteria = session.createCriteria(Blog.class);
			criteria.add(Restrictions.ge("id", maxId - 5));
			blogList = criteria.list();
		} catch (HibernateException e) {
			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
		} catch (NullPointerException e) {
			return null;
		} finally {
			session.close();
		}
		return blogList;
	}
}
