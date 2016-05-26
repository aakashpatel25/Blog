package com.Blog.Dao.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.Blog.Dao.BlogDao;
import com.Blog.Dao.LikesDao;
import com.Blog.Dao.UsersDao;
import com.Blog.Service.IBlog;
import com.Blog.Service.ILikes;
import com.Blog.Service.IUsers;
import com.Blog.beans.Blog;
import com.Blog.beans.Users;

public class LikesDaoTest {
	/**
	 * LikesDao.
	 */
	private ILikes likeDao;
	
	/**
	 * BlogDao.
	 */
	private IBlog blogDao;
	
	/**
	 * UserDao.
	 */
	private IUsers userDao;
	
	/**
	 * User object.
	 */
	private Users user;
	
	/**
	 * Blog object.
	 */
	private Blog blog;
	
	/**
	 * Constant for username.
	 */
	private static final String USER_NAME = "Writer";
	
	/**
	 * Constant for password.
	 */
	private static final String PASSWORD = "admin123";
	
	/**
	 * Constant for first name of user.
	 */
	private static final String FIRST_NAME = "User";
	
	/**
	 * Constant for last name of user.
	 */
	private static final String LAST_NAME = "Users";
	
	/**
	 * Constant for email of user.
	 */
	private static final String EMAIL = "admin@admin.com";
	
	/**
	 * Constant for Blog titles. 
	 */
	private static final String TITLE = "Blog 1";
	
	/**
	 * Constants for Post titles.
	 */
	private static final String POST = "Post 1";
	
	/**
	 * Constructor of LikesDaoTest.
	 */
	public LikesDaoTest(){
		likeDao = new LikesDao();
		blogDao = new BlogDao();
		userDao = new UsersDao();
	}
	
	/**
	 * Set up the test environment before testing.
	 */
	@Before
	public void setUp(){
		user = new Users(USER_NAME,PASSWORD,FIRST_NAME,LAST_NAME,EMAIL);
		userDao.createNewUser(user);
		blog = new Blog(user,TITLE,POST);
		blogDao.insertBlog(blog);
	}
	
	/**
	 * To test if the setLike functions works properly.
	 */
	@Test
	public void testSetLike(){
		final boolean result = likeDao.setLikes(blog, user);
		final boolean unset = likeDao.unlike(blog, user);
		assertTrue(result);
		assertTrue(unset);
	}
	
	/**
	 * To test if getLikesOnBlog works properly.
	 */
	@Test
	public void testGetLikesOnBlog(){
		final boolean result = likeDao.setLikes(blog, user);
		final long likes = likeDao.getLikesOnBlog(blog);
		final boolean unlike = likeDao.unlike(blog, user);
		assertEquals(likes,1);
		assertTrue(result);
		assertTrue(unlike);
	}
	
	/**
	 * To check is get like by user function works properly.
	 */
	@Test
	public void testGetLikeByUser(){
		final boolean result = likeDao.setLikes(blog, user);
		final List<Users> userList = likeDao.getBlogLikesByUser(blog);
		final boolean unlike = likeDao.unlike(blog, user);
		assertEquals(userList.size(),1);
		assertEquals(userList.get(0).getUsername(),USER_NAME);
		assertEquals(userList.get(0).getEmail(),EMAIL);
		assertTrue(result);
		assertTrue(unlike);
	}
	
	/**
	 * To test if unlike function works properly.
	 */
	@Test
	public void testUnlikeZero(){
		final boolean result = likeDao.unlike(blog, user);
		assertFalse(result);
	}
	
	/**
	 * To test if the unlike functions works properly.
	 */
	@Test
	public void testUnlike(){
		final boolean result = likeDao.setLikes(blog, user);
		final boolean unset = likeDao.unlike(blog, user);
		assertTrue(result);
		assertTrue(unset);
	}
	
	/**
	 * Clean up the environment after testing.
	 */
	@After
	public void cleanUp(){
		blogDao.deleteBlog(blogDao.getBlogId(user, TITLE));
		userDao.deleteUser(userDao.findUserId(USER_NAME));
	}
}
