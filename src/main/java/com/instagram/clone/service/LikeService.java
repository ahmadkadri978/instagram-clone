package com.instagram.clone.service;

import com.instagram.clone.entity.Like;
import com.instagram.clone.entity.Post;
import com.instagram.clone.entity.User;
import com.instagram.clone.repository.LikeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class LikeService {

    @Autowired
    private LikeRepository likeRepository;

    public Like addLike(Like like) {
        return likeRepository.save(like);
    }
    @Transactional
    public void removeLike(User user, Post post) {
        likeRepository.deleteByUserAndPost(user, post);
    }

    public boolean isPostLikedByUser(User user, Post post) {
        return likeRepository.existsByUserAndPost(user, post);
    }
}
