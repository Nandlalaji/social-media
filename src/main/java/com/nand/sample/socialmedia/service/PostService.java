package com.nand.sample.socialmedia.service;

import java.util.List;
import java.util.Map;
import java.util.TreeSet;

import com.nand.sample.socialmedia.model.Post;
import com.nand.sample.socialmedia.model.User;

/**
 * Interface to hold method related to simple social media 
 * 
 * @author Nand
 * @since 23th Sept 2019
 */
public interface PostService {
	/**
	 * Creates new post for particular user
	 * 
	 * @param userId
	 * @param postId
	 * @param content
	 * @return
	 * @throws Exception
	 */
	Map<Post, Integer> createPost(Integer userId, Integer postId, String content) throws Exception;

	/**
	 * Get New Feed which user and its follower has posted recently.
	 * Posts are sorted with date it posted
	 * 
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	List<String> getNewsFeed(Integer userId) throws Exception;

	/**
	 * User can follow any user
	 * 
	 * @param followerId
	 * @param followeeId
	 * @return
	 * @throws Exception
	 */
	TreeSet<User> follow(Integer followerId, Integer followeeId) throws Exception;

	/**
	 * User can unfollow any user
	 * 
	 * @param followerId
	 * @param followeeId
	 * @return
	 * @throws Exception
	 */
	TreeSet<User> unfollow(Integer followerId, Integer followeeId) throws Exception;

	/**
	 * User id is assigned via logic and is not input by user
	 * creates new user
	 * 
	 * @param user
	 */
	void setusers(User user);

	/**
	 * get lasted post id. id is not inputed by user
	 * Handle by logic
	 * @return
	 */
	Integer getLastestPostId();

	/**
	 * get all users
	 * @return
	 */
	TreeSet<User> getUsers();

	/**
	 * get all post
	 * @return
	 */
	TreeSet<Post> getAllPost();
	
	/**
	 * get all post user mapping
	 * @return
	 */
	Map<Post,Integer> getPostUserMapping();
}
