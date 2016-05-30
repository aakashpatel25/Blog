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

	/**
	 * To insert a blog in the databse.
	 * 
	 * @param Blog
	 *            Blog that is to be inserted in the database.
	 * @return boolean True or false depending on the success or failure of the operation.
	 */
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

	/**
	 * To edit the title of the blog.
	 * 
	 * @param newTitle
	 *            Updated title of the blog.
	 * @param blogId
	 *            BlogId of the blog for which the title is to be updated.
	 * @return boolean True or false depending on the success of the operation.
	 */
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

	/**
	 * To edit the content of the blog post.
	 * 
	 * @param newPost
	 *            Modified post of the blog.
	 * @param blogId
	 *            BlogId of the blog for which the post is to be modified.
	 * @return boolean True or false depending on the success or failure of the operation.
	 */
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

	/**
	 * Get Blog from the blogId of the blog.
	 * 
	 * @param blogId
	 *            BlogId of the blog that is to be fetched.
	 * @return Blog Blog that has the given blogId.
	 */
	@Override
	public Blog getBlog(long blogId) {
		Session session = factory.openSession();
		String hql = "from Blog as b where b.id = :blogId";
		Query query = session.createQuery(hql).setParameter("blogId", blogId);
		Blog blog = (Blog) query.uniqueResult();
		return blog;
	}

	/**
	 * To delete a blog.
	 * 
	 * @param blogId
	 *            BlogId of the blog that is to be deleted.
	 * @return boolean True or false depending upon the success of failure of the operation.
	 */
	@Override
	public boolean deleteBlog(long blogId) {
		Session session = factory.openSession();
		int result = session.createQuery("Delete Blog where id = :blogId")
				.setParameter("blogId", blogId).executeUpdate();
		session.close();
		return result > 0;
	}

	/**
	 * To get the blogId of the given blog.
	 * 
	 * @param username
	 *            Username of the user who has written the post.
	 * @param title
	 *            Title of the blog.
	 * @return long BlogId of the given blog.
	 */
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

	/**
	 * To fetch the latest five blogs from the database.
	 * 
	 * @return List<Blog> List of blog that are uploaded recently.
	 */
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
