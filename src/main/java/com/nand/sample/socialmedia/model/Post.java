package com.nand.sample.socialmedia.model;

import java.time.LocalDateTime;

/**
 * POJO class for object of post
 * 
 * @author Nand
 * @since 23th Sept 2019
 */
public class Post implements Comparable<Post> {

	Integer postId;
	
	String content;

	LocalDateTime dateTime;

	public Integer getPostId() {
		return postId;
	}

	public void setPostId(Integer postId) {
		this.postId = postId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public LocalDateTime getDateTime() {
		return dateTime;
	}

	public void setDateTime(LocalDateTime dateTime) {
		this.dateTime = dateTime;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((postId == null) ? 0 : postId.hashCode());
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
		Post other = (Post) obj;
		if (postId == null) {
			if (other.postId != null)
				return false;
		} else if (!postId.equals(other.postId))
			return false;
		return true;
	}

	@Override
	public int compareTo(Post o) {
		return this.getPostId() - o.getPostId();
	}

	@Override
	public String toString() {
		return "Post [postId=" + postId + ", content=" + content + ", dateTime=" + dateTime + "]";
	}

	

}
