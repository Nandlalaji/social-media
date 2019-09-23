package com.nand.sample.socialmedia.serviceImpl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

import org.springframework.stereotype.Service;

import com.nand.sample.socialmedia.model.Post;
import com.nand.sample.socialmedia.model.User;
import com.nand.sample.socialmedia.service.PostService;
import com.nand.sample.socialmedia.testdata.PostUserMappingTestData;
import com.nand.sample.socialmedia.testdata.UserTestData;

/**
 * Implement class for PostService
 * 
 * @author Nand
 * @since 23th Sept 2019
 */
@Service
public class PostServiceImpl implements PostService {

	static TreeSet<User> userList = new TreeSet<>();
	static TreeSet<Post> posts = new TreeSet<>();
	static Map<Post, Integer> postUserMapping = new HashMap<>();

	@Override
	public Map<Post, Integer> createPost(Integer userId, Integer postId, String content) throws Exception {
		Post post = new Post();
		post.setPostId(postId);
		post.setContent(content);
		post.setDateTime(LocalDateTime.now());
		posts.add(post);
		getPostUserMapping().put(post, userId);

		return postUserMapping;
	}

	@Override
	public List<String> getNewsFeed(Integer userId) throws Exception {

		List<String> contents = new ArrayList<>();

		List<Integer> userToCheck = new ArrayList<>();

		// sort post with date
		List<Map.Entry<Post, Integer>> list = new LinkedList<Map.Entry<Post, Integer>>(getPostUserMapping().entrySet());

		Collections.sort(list, new Comparator<Map.Entry<Post, Integer>>() {
			public int compare(Map.Entry<Post, Integer> o1, Map.Entry<Post, Integer> o2) {
				return (o2.getKey().getDateTime()).compareTo(o1.getKey().getDateTime());
			}
		});

		for (Map.Entry<Post, Integer> p : list) {
			System.out.println(p.getKey() + "---- User id " + p.getValue());
		}
		// put userid as well as follower in one arraylist
		for (User user : getUsers()) {
			if (user.getUserId() == userId) {
				userToCheck.add(userId);
				List<Integer> followers = user.getFollowers();
				if (followers != null) {
					for (Integer follower : followers) {
						userToCheck.add(follower);

					}
				}
			}
		}

		// traverse sorted post to find result content list
		for (Map.Entry<Post, Integer> entry : list) {
			if (userToCheck.contains(entry.getValue())) {
				contents.add(entry.getKey().getContent());
			}
			if (contents.size() >= 20) {
				break;
			}

		}
		return contents;
	}

	@Override
	public TreeSet<User> follow(Integer followerId, Integer followeeId) throws Exception {
		TreeSet<User> tempUser = getUsers();
		for (User user : tempUser) {
			Boolean isNotPresent = true;
			if (user.getUserId() == followeeId) {
				for (Integer flrId : user.getFollowers()) {
					if (flrId == followerId) {
						isNotPresent = false;
						break;
					}
				}
				//adds only if it is not existing follower
				if (isNotPresent) {
					List<Integer> tempfollowerId = user.getFollowers();
					tempfollowerId.add(followerId);
					user.setFollowers(tempfollowerId);
				}
			}
		}
		return tempUser;
	}

	@Override
	public TreeSet<User> unfollow(Integer followerId, Integer followeeId) throws Exception {
		TreeSet<User> tempUser = getUsers();
		for (User user : tempUser) {
			if (user.getUserId() == followeeId) {
				for (Integer flrId : user.getFollowers()) {
					if (flrId == followerId) {
						List<Integer> tempfollowerId = user.getFollowers();
						tempfollowerId.remove(followerId);
						user.setFollowers(tempfollowerId);
					}
				}
			}
		}
		return tempUser;
	}

	@Override
	public void setusers(User user) {
		user.setUserId(userList.last().getUserId() + 1);
		userList.add(user);
	}

	@Override
	public Integer getLastestPostId() {
		if (posts.size() == 0)
			return 0;
		return posts.last().getPostId();
	}

	@Override
	public TreeSet<User> getUsers() {
		userList = UserTestData.getUser();
		return userList;
	}

	@Override
	public TreeSet<Post> getAllPost() {
		return posts;
	}

	@Override
	public Map<Post, Integer> getPostUserMapping() {
		postUserMapping = PostUserMappingTestData.getpostUserMapping();
		return postUserMapping;
	}
}
