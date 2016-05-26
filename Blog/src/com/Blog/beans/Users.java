package com.Blog.beans;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class Users {

	/**
	 * Unique user number.
	 */
	long userId;

	/**
	 * Unique username of the user. Also display name on blogs and comments.
	 */
	String username;

	/**
	 * Password of the user.
	 */
	String password;

	/**
	 * Date when the user signed up.
	 */
	Date userSince;

	/**
	 * First name of the user.
	 */
	String firstName;

	/**
	 * Last name of the user.
	 */
	String lastName;

	/**
	 * Email address of the user.
	 */
	String email;

	/**
	 * Blogs written by user.
	 */
	Set<Blog> blogs;

	/**
	 * Comments written by user
	 */
	Set<Comments> commentsList;

	/**
	 * Blogs liked by the user.
	 */
	Set<Likes> likesList;

	/**
	 * User constructor. New way to create user.
	 * 
	 * @param id
	 *            Id of the user.
	 * @param uname
	 *            Username of the user.
	 * @param pword
	 *            Password of the user.
	 * @param fName
	 *            First name of the user.
	 * @param lName
	 *            LastName of the user.
	 * @param emailAdd
	 *            Email address of the User.
	 */
	public Users(String uname, String pword, String fName, String lName, String emailAdd) {
		username = uname;
		password = pword;
		firstName = fName;
		lastName = lName;
		email = emailAdd;
		userSince = new Date();
		blogs = new HashSet<Blog>();
		commentsList = new HashSet<Comments>();
		likesList = new HashSet<Likes>();
	}	
	
	/**
	 * Default user constructor.
	 */
	public Users(){
		
	}

	/**
	 * Get the userId of the user.
	 * 
	 * @return long long userId.
	 */
	public long getUserId() {
		return userId;
	}

	/**
	 * 
	 * @param userId
	 */
	public void setUserId(long userId) {
		this.userId = userId;
	}

	/**
	 * Get the username of the user.
	 * 
	 * @return String returns the username.
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * Set the username of the user. Maily to edit the username.
	 * 
	 * @param username
	 *            Sets the username of the user.
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * Returns the password of the user.
	 * 
	 * @return String. Value of the password.
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Set the password of the user. Mainly to change the password.
	 * 
	 * @param password
	 *            New password.
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * Returns the user since.
	 * 
	 * @return Date Date when the user signed up for the website.
	 */
	public Date getUserSince() {
		return userSince;
	}

	/**
	 * Set the time when user signed up for the blog.
	 * 
	 * @param userSince
	 *            Date userSince.
	 */
	public void setUserSince(Date userSince) {
		this.userSince = userSince;
	}

	/**
	 * Returns the first name of the user.
	 * 
	 * @return String First name of the user.
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * Sets the first name of the user.
	 * 
	 * @param firstName
	 *            First name of the user.
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * Returns the last name of the user.
	 * 
	 * @return String Last name of the user.
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * Sets the lats name of the user.
	 * 
	 * @param lastName
	 *            Last name of the user.
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * Returns the email of the user.
	 * 
	 * @return String email of the user.
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Sets the email of the user.
	 * 
	 * @param email
	 *            email of the user.
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Returns the list of blogsIds created by user.
	 * 
	 * @return Set<Blog> List of blogs created by user.
	 */
	public Set<Blog> getBlogs() {
		return blogs;
	}

	/**
	 * Set the list of blogs posted by user.
	 * 
	 * @param blogs
	 *            Set<Blogs> consisting of the blogs written by user.
	 */
	public void setBlogs(Set<Blog> blogs) {
		this.blogs = blogs;
	}

	/**
	 * Returns the list of comments posted by user.
	 * 
	 * @return Set<Comments> List of comments posted by user.
	 */
	public Set<Comments> getCommentsList() {
		return commentsList;
	}

	/**
	 * Set the list of Comments written by user.
	 * 
	 * @param comments
	 *            Set<Comments> consisting of the Comments posted by user.
	 */
	public void setCommentsList(Set<Comments> comments) {
		this.commentsList = comments;
	}

	/**
	 * Set the list of blogIds liked by user.
	 * 
	 * @return Set<Likes> 
	 * 			Set<Likes> consisting of the blogs liked by
	 */
	public Set<Likes> getLikesList() {
		return likesList;
	}

	/**
	 * Set the list of blogId created by user.
	 * 
	 * @param likes
	 *            Set<Likes> consisting of the blogs liked by user.
	 */
	public void setLikesList(Set<Likes> likes) {
		this.likesList = likes;
	}
}