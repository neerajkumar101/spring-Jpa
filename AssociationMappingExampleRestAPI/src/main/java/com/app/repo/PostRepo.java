package com.app.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.entity.Post;

@Repository
public interface PostRepo extends JpaRepository<Post, Long> {

}
