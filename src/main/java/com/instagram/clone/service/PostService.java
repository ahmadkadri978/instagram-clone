package com.instagram.clone.service;

import com.instagram.clone.dto.PostDto;
import com.instagram.clone.entity.Post;
import com.instagram.clone.entity.User;
import com.instagram.clone.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    public Post createPost(Post post) {
        return postRepository.save(post);
    }
    public Optional<Post> getPostById(Long id){
        return postRepository.findById(id);
    }

    public Page<Post> getPostsByUser(User user , Pageable pageable) {
        return postRepository.findByUser(user , pageable);
    }

    public Page<PostDto> searchPosts(String keyword, Pageable pageable) {
        Page<Post> posts = postRepository.findByCaptionContainingIgnoreCase(keyword, pageable);
        return posts.map(post -> new PostDto(post.getId(), post.getCaption(), post.getImageUrl()));
    }
}
