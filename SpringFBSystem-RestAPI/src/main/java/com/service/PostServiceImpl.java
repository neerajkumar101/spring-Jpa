package com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.domain.entities.Post;
import com.domain.entities.User;
import com.domain.repositories.PostRepo;
import com.domain.repositories.UserRepo;

@Service("postService")
public class PostServiceImpl implements PostServiceInterface{

	@Autowired
	private PostRepo postRepo;
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private PostServiceInterface postService;
	
	@Autowired
	private CommentServiceInterface commService;

	@Override
	public void savePost(Post post) {
		post.setLikeCount(post.getLikeCount());
		post.setUser(post.getUser());
		post.setPostText(post.getPostText());
		post.setComments(post.getComments());
		
		post.setCommentCount(new Long(0));
		post.setShareCount(new Long(0));
		
		postRepo.save(post);
	}

	@Override
	public List<Post> findPostsByUserId(User user) {
		return postRepo.findPostsByUserId(user);
	}

	@Override
	public Long doLike(Long postId, Long likeCount) {
		Post post = (Post) postRepo.findPostsByPostId(postId);
		System.out.println("before increasing like count: " + post);
		Long increseLikeCount = (Long) likeCount + 1;
		
		post.setLikeCount(increseLikeCount);
		
		postService.savePost(post);
		System.out.println("After increasing like count: " + post);
		return increseLikeCount;
	}
	
	@Override
	public void doShare(Post post, User user, String aboutShare) {
		user.getSharedPost().add(post);
		userRepo.save(user);
		
		/*post.getSharedBy().add(user);
		postRepo.save(post);*/
		
//		System.out.println("before saving user  in doShare");
//		userRepo.save(user);
//		System.out.println("after saving user");
	}

	@Override
	public void deletePost(Long postId) {
		postRepo.delete(postId);
	}

}
