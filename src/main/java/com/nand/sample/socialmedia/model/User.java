package com.nand.sample.socialmedia.model;

import java.util.List;

/**
 * POJO class for object of User
 * 
 * @author Nand
 * @since 23th Sept 2019
 */
public class User implements Comparable<User> {

	Integer UserId;

	String name;

	String email;

	List<Integer> followers;

	public Integer getUserId() {
		return UserId;
	}

	public void setUserId(Integer userId) {
		UserId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<Integer> getFollowers() {
		return followers;
	}

	public void setFollowers(List<Integer> followers) {
		this.followers = followers;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((UserId == null) ? 0 : UserId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (UserId == null) {
			if (other.UserId != null)
				return false;
		} else if (!UserId.equals(other.UserId))
			return false;
		return true;
	}

	@Override
	public int compareTo(User arg0) {
		return this.getUserId() - arg0.getUserId();
	}

}
