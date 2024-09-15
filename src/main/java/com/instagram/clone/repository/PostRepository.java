package com.instagram.clone.repository;

import com.instagram.clone.entity.Post;
import com.instagram.clone.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    Page<Post> findByUser(User user , Pageable pageable);

    Page<Post> findByCaptionContainingIgnoreCase(String keyword, Pageable pageable);
}
