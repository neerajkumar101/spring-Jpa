package com.nraj.authapi.repo;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.nraj.authapi.entity.Post;
import com.nraj.authapi.entity.User;

@Repository("postRepo")
public interface PostRepo extends CrudRepository<Post, Long> {
	
	@Query("FROM Post p where p.postId = ? ORDER BY p.postId DESC")
	public Post findPostByPostId(Long postId);
	
	@Query("FROM Post p where p.user = ? ORDER BY p.postId DESC")
	public List<Post> findPostsByUserId(User user);
	
	@Query("FROM Post p ORDER BY p.postId DESC")
	public List<Post> findAll();
	
	@Query("FROM Post p where p.postId = ? ORDER BY p.postId DESC")
	public Post findPostById(Long postId);
	
}
