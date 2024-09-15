package com.instagram.clone.repository;

import com.instagram.clone.entity.Like;
import com.instagram.clone.entity.Post;
import com.instagram.clone.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikeRepository extends JpaRepository<Like, Long> {
    boolean existsByUserAndPost(User user, Post post);
    void deleteByUserAndPost(User user, Post post);
}
