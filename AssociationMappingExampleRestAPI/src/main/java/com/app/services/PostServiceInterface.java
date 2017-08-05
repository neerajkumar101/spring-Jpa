package com.app.services;

import java.util.List;

import com.app.entity.Post;
import com.app.entity.User;

public interface PostServiceInterface {
	public Post publishPost(User user, Post post);
	public Post getPostById(Long postId);
	public List<Post> getAll();
	public void deletePost(Long postId);
	public Post sharePost(User sharingUser, Post post);
}
