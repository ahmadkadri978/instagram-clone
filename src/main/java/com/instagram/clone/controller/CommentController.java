package com.instagram.clone.controller;

import com.instagram.clone.dto.CommentDto;
import com.instagram.clone.dto.PostDto;
import com.instagram.clone.dto.UserDto;
import com.instagram.clone.entity.Comment;
import com.instagram.clone.entity.Like;
import com.instagram.clone.entity.Post;
import com.instagram.clone.entity.User;
import com.instagram.clone.error.ResourceNotFoundException;
import com.instagram.clone.service.CommentService;
import com.instagram.clone.service.LikeService;
import com.instagram.clone.service.PostService;
import com.instagram.clone.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/posts")
public class CommentController {

    @Autowired
    private PostService postService;

    @Autowired
    private CommentService commentService;

    @Autowired
    private UserService userService;
    @Autowired
    private LikeService likeService;

    // إنشاء تعليق جديد
    @PostMapping("/{postId}/comments")
    public ResponseEntity<CommentDto> addComment(@PathVariable Long postId,
                                              @RequestParam String username,
                                              @RequestBody Comment comment) {
        Post post = postService.getPostById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("Post not found with id: " + postId));
        User user = userService.getUserByUsername(username);
        if (user == null) {
            throw new ResourceNotFoundException("User not found with username: " + username);
        }

        comment.setPost(post);
        comment.setUser(user);
        Comment newComment = commentService.addComment(comment);

        UserDto userDTO = new UserDto(user.getId(), user.getUsername(), user.getEmail());
        PostDto postDTO = new PostDto(post.getId(), post.getCaption(), post.getImageUrl());
        CommentDto commentDTO = new CommentDto(newComment.getId(), newComment.getText(), userDTO, postDTO);

        return ResponseEntity.ok(commentDTO);
    }
    @GetMapping("/{postId}/comments")
    public ResponseEntity<List<CommentDto>> getCommentsByPost(@PathVariable Long postId) {

        Post post = postService.getPostById(postId).orElseThrow(()-> new ResourceNotFoundException("Post not found with id: " + postId));
        List<Comment> comments = commentService.getCommentsByPost(post);

        List<CommentDto> commentDtos = comments.stream().map(comment -> {
            User user = comment.getUser();
            UserDto userDTO = new UserDto(user.getId(), user.getUsername(), user.getEmail());

            PostDto postDTO = new PostDto(post.getId(), post.getCaption(), post.getImageUrl());

            return new CommentDto(comment.getId(), comment.getText(), userDTO, postDTO);
        }).toList();

        return ResponseEntity.ok(commentDtos);
    }

    // إضافة إعجاب
    @PostMapping("/{postId}/like")
    public ResponseEntity<String> addLike(@PathVariable Long postId, @RequestParam String username) {
        Post post = postService.getPostById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("Post not found with id: " + postId));
        User user = userService.getUserByUsername(username);
        if (user == null) {
            throw new ResourceNotFoundException("User not found with username: " + username);
        }

        if (likeService.isPostLikedByUser(user, post)) {
            return ResponseEntity.badRequest().body("Already liked");
        }

        Like like = new Like();
        like.setUser(user);
        like.setPost(post);
        likeService.addLike(like);

        return ResponseEntity.ok("Liked");
    }

    // إزالة إعجاب
    @DeleteMapping("/{postId}/like")
    public ResponseEntity<String> removeLike(@PathVariable Long postId, @RequestParam String username) {
        Post post = postService.getPostById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("Post not found with id: " + postId));

        User user = userService.getUserByUsername(username);
        if (user == null) {
            throw new ResourceNotFoundException("User not found with username: " + username);
        }


        if (!likeService.isPostLikedByUser(user, post)) {
            return ResponseEntity.badRequest().body("Not liked yet");
        }

        likeService.removeLike(user, post);

        return ResponseEntity.ok("Like removed");
    }

}
