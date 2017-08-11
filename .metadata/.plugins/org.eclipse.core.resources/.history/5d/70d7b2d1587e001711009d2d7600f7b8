package com.app.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.entity.Post;
import com.app.entity.User;
import com.app.repo.PostRepo;

@Service
public class PostServiceImpl implements PostServiceInterface {

	@Autowired
	private PostRepo postRepo;
	
	@Override
	public Post publishPost(User user, Post post) {
		post.setLikeCount(new Long(0));
		post.setShareCount(new Long(0));
		post.setUser(user);
		Post postSaved = postRepo.save(post);
//		user.getPosts().add(post);
//		userRepo.save(user);
		return postSaved;
	}

	@Override
	public Post getPostById(Long postId) {
		Post post = postRepo.findOne(postId);
		return post;
	}

	@Override
	public List<Post> getAll() {
		List<Post> list = postRepo.findAll();
		return list;
	}

	@Override
	public void deletePost(Long postId) {
		postRepo.delete(postId);
	}

	@Override
	public Post sharePost(User sharingUser, Post post) {
		sharingUser.addPost(post);
		post.setSharedBy(sharingUser);
		
//		post.setSharingUser(sharingUser);
		
//		post.getSharedBy().add(sharingUser);
		postRepo.save(post);
		return post;
	}
}
