package com.Blog.Dao.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.Blog.Dao.BlogDao;
import com.Blog.Dao.CommentsDao;
import com.Blog.Dao.UsersDao;
import com.Blog.Service.IBlog;
import com.Blog.Service.IComments;
import com.Blog.Service.IUsers;
import com.Blog.beans.Blog;
import com.Blog.beans.Comments;
import com.Blog.beans.Users;

public class CommentsDaoTest {
	/**
	 * CommentsDao.
	 */
	private IComments commentDao;
	
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
	 * List of comments.
	 */
	private static final String[] MESSAGE = {"Comment 1","Comment 2","Comment 3","Comment 4"};
	
	
	
	
	/**
	 * Constructor of CommentsDaoTest.
	 */
	public CommentsDaoTest(){
		commentDao = new CommentsDao();
		userDao = new UsersDao();
		blogDao = new BlogDao();
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
	 * Create comment object.
	 */
	private Comments createComment(){
		Comments comment = new Comments(user,blog,MESSAGE[0]);
		return comment;
	}
	
	/**
	 * Create list of comment objects.
	 */
	private List<Comments> createCommentList(){
		List<Comments> commentList = new ArrayList<Comments>();
		for(int i=0;i<MESSAGE.length;i++){
			commentList.add(new Comments(user,blog,MESSAGE[i]));
		}
		return commentList;
	}
	
	/**
	 * Tests if addComment function works properly.
	 */
	@Test
	public void testAddComment(){
		final boolean result = commentDao.addComment(createComment());
		final long id = commentDao.getCommentId(user, blog);
		final boolean delete = commentDao.deleteComment(id);
		assertTrue(result);
		assertTrue(id>0);
		assertTrue(delete);
	}
	
	/**
	 * Test if editComment function works properly.
	 */
	@Test
	public void testEditComment(){
		final boolean result = commentDao.addComment(createComment());
		final long id = commentDao.getCommentId(user, blog);
		final boolean updated = commentDao.editComment(id, MESSAGE[1]);
		final boolean delete = commentDao.deleteComment(id);
		assertTrue(result);
		assertTrue(id>0);
		assertTrue(updated);
		assertTrue(delete);
	}
	
	/**
	 * Test if deleteComment function returns false when nonexistent comment is deleted.
	 */
	@Test
	public void testDeleteCommentZero(){
		final boolean result = commentDao.deleteComment(0);
		assertFalse(result);
	}
	
	/**
	 * Test if deleteComment function works properly.
	 */
	@Test
	public void testDeleteComment(){
		final boolean result = commentDao.addComment(createComment());
		final long id = commentDao.getCommentId(user, blog);
		final boolean delete = commentDao.deleteComment(id);
		assertTrue(result);
		assertTrue(id>0);
		assertTrue(delete);
	}
	
	/**
	 * Test if getCommentBy id works properly.
	 */
	@Test
	public void testGetCommentByIdNotExisting(){
		final long id = commentDao.getCommentId(user, blog);
		assertEquals(id,0);
	}
	
	/**
	 * Test if getCommentById function works properly.
	 */
	@Test
	public void testGetCommentById(){
		final boolean result = commentDao.addComment(createComment());
		final long id = commentDao.getCommentId(user, blog);
		final boolean delete = commentDao.deleteComment(id);
		assertTrue(result);
		assertTrue(id>0);
		assertTrue(delete);
	}
	
	/**
	 * Test if deleteBlogComment works.
	 */
	@Test
	public void testDeleteAllComments(){
		final List<Comments> comments = createCommentList();
		for(int i=0;i<comments.size();i++){
			commentDao.addComment(comments.get(i));
		}
		final boolean result  = commentDao.deleteBlogComments(blog);
		final long id  = commentDao.getCommentId(user, blog);
		assertTrue(result);
		assertEquals(id,0);
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
