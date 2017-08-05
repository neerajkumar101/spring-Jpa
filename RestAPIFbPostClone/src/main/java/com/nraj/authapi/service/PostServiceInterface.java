package com.nraj.authapi.service;

import java.util.List;

import com.nraj.authapi.entity.Post;
import com.nraj.authapi.entity.User;

public interface PostServiceInterface {
	public Post findPostById(Long postId);
	public List<Post> findAllPosts(); 
	public List<Post> findPostsByUserId(User userId);
	public Post savePost(Post post);
	public Long doLike(Long postId, Long LikeCount);
	public void deletePostById(Long postId);
	public void doShare(Post post, User user, String aboutShare);
}
