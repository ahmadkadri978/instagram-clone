package com.instagram.clone.repository;

import com.instagram.clone.entity.Comment;
import com.instagram.clone.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment , Long> {
    List<Comment> findByPost(Post post);
}
