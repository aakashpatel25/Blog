package com.Blog.Service;

import java.util.List;

import com.Blog.beans.Blog;
import com.Blog.beans.Users;

public interface ILikes {

	/**
	 * Sets the likes of the blog.
	 * 
	 * @param likes
	 *            number of likes.
	 * @param blogId
	 *            blogId of the blog on which like are to be set.
	 * @param userId
	 *            userId of the person who has liked the post.
	 * @return boolean Success of Failure.
	 */
	public boolean setLikes(Blog blog, Users user);

	/**
	 * Gets the number of likes on the blog.
	 * 
	 * @param blogId
	 *            BlogId on which the like are to be obtained.
	 * @return long number of likes on the blog.
	 */
	public long getLikesOnBlog(Blog blog);

	/**
	 * To get the userIds of the users that liked a particular blog.
	 * 
	 * @param blogId
	 *            BlogId for which the like list is to be retrieved.
	 * @return List<Long> List of the userIds of the user who liked the blog.
	 */
	public List<Users> getBlogLikesByUser(Blog blog);

	/**
	 * To unlike a particular blog.
	 * 
	 * @param blog
	 *            Blog which is to be unliked.
	 * @param user
	 *            User who is unliking the blog.
	 * @return boolean True or false depending on the success of operation.
	 */
	public boolean unlike(Blog blog, Users user);
}
