package com.nand.sample.socialmedia.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.nand.sample.socialmedia.model.Post;
import com.nand.sample.socialmedia.model.User;
import com.nand.sample.socialmedia.service.PostService;
/**
 * This is controller class to interact with user
 * it has used PostService to all operation
 * 
 * @author Nand
 * @since 23th Sept 2019
 */
@RestController
public class UserInterfaceController {

	PostService post;

	/**
	 * set PostService via autowiring
	 * 
	 * @param post
	 */
	@Autowired
	public void setPostService(PostService post) {
		this.post = post;
	}

	/**
	 * Post method to add new user
	 * 
	 * @param user
	 */
	@PostMapping(path = "/inputUser", consumes = "application/json", produces = "application/json")
	public void addUser(@RequestBody User user) {
		this.post.setusers(user);
	}

	/**
	 * Put method to add new post
	 * 
	 * @param userId
	 * @param content
	 */
	@PutMapping("/inputPost/{userId}/{content}")
	public void addPost(@PathVariable Integer userId, @PathVariable String content) throws Exception {
		checkForUser(userId);
		this.post.createPost(userId, this.post.getLastestPostId() + 1, content);
	}

	/**
	 * Get method to get related post for given particular user
	 * 
	 * @param userId
	 */
	@GetMapping("/getNewFeed/{userId}")
	public List<String> getNewsFeed(@PathVariable Integer userId) throws Exception {
		checkForUser(userId);
		return this.post.getNewsFeed(userId);
	}

	/**
	 * Get method to get allUser
	 * 
	 */
	@GetMapping("/getAllUser")
	public TreeSet<User> getAllUser() throws Exception {
		return this.post.getUsers();
	}

	/**
	 * Get method to get post
	 * 
	 */
	@GetMapping("/getAllPost")
	public TreeSet<Post> getAllPost() throws Exception {
		return this.post.getAllPost();
	}

	/**
	 * Private method to check existing user
	 * 
	 * @param userId
	 */
	private void checkForUser(Integer userId) throws Exception {
		List<Integer> idList = new ArrayList<>();
		for (User user : this.post.getUsers()) {
			idList.add(user.getUserId());
		}
		if (!idList.contains(userId)) {
			throw new Exception("This userId is not found");
		}
	}
}
