package com.Blog.beans;

import java.util.Date;

public class Likes {
	/**
	 * Like Id.
	 */
	long likeId;

	/**
	 * User who liked the post.
	 */
	Users user;

	/**
	 * Blog that was liked by the user.
	 */
	Blog blog;

	/**
	 * Timestamp when the activity was performed
	 */
	Date time;

	/**
	 * Default Constructor.
	 */
	public Likes() {

	}

	/**
	 * To create a like object, modified constructor.
	 * 
	 * @param blg
	 *            Blog object.
	 * @param usr
	 *            User object.
	 * @param date
	 *            Date when the blog was liked.
	 * @return Likes Likes Object.
	 */
	public Likes(Blog blg, Users usr) {
		this.blog = blg;
		this.user = usr;
		this.time = new Date();
	}

	/**
	 * Gets the User who liked the blog.
	 * 
	 * @return User user.
	 */
	public Users getUser() {
		return user;
	}

	/**
	 * Sets the user.
	 * 
	 * @param usr
	 *            User who liked the blog.
	 */
	public void setUser(Users usr) {
		this.user = usr;
	}

	/**
	 * Gets a Blog that is being liked by the user
	 * 
	 * @return Blog Blog.
	 */
	public Blog getBlog() {
		return blog;
	}

	/**
	 * Sets a blog.
	 * 
	 * @param Blog
	 *            Blog that is being liked.
	 */
	public void setBlog(Blog blg) {
		this.blog = blg;
	}

	/**
	 * Returns the time when the activity was performed.
	 * 
	 * @return Date Datetime of the activity.
	 */
	public Date getTime() {
		return time;
	}

	/**
	 * Sets the time of the activity.
	 * 
	 * @param time
	 *            DateTime of the activity!
	 */
	public void setTime(Date time) {
		this.time = time;
	}

	/**
	 * Set the id of the like.
	 * 
	 * @param id
	 *            Id of the like.
	 */
	public void setLikeId(long id) {
		this.likeId = id;
	}

	/**
	 * Returns the like id.
	 * 
	 * @return long Id of the like.
	 */
	public long getLikeId() {
		return likeId;
	}
}
