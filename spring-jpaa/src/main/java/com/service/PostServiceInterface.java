package com.service;

import java.util.List;

import com.domain.entities.Post;
import com.domain.entities.User;

public interface PostServiceInterface {
	public List<Post> findPostsByUserId(User userId);
	public void savePost(Post post);
	public Long doLike(Long postId, Long LikeCount);
	public void deletePost(Long postId);
	public void doShare(Post post, User user, String aboutShare);
}
