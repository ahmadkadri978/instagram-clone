package com.instagram.clone.controller;

import com.instagram.clone.dto.PostDto;
import com.instagram.clone.dto.UserDto;
import com.instagram.clone.entity.Post;
import com.instagram.clone.entity.User;
import com.instagram.clone.error.ResourceNotFoundException;
import com.instagram.clone.service.PostService;
import com.instagram.clone.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/posts")
public class PostController {

    @Autowired
    private PostService postService;

    @Autowired
    private UserService userService;

    // إنشاء منشور جديد
    @PostMapping("/{username}/create")
    public ResponseEntity<PostDto> createPost(@PathVariable String username, @RequestBody Post post) {
        User user = userService.getUserByUsername(username);
        // إذا لم يتم العثور على المستخدم، يتم طرح استثناء ResourceNotFoundException
        if (user == null) {
            throw new ResourceNotFoundException("User not found with username: " + username);
        }

        // إنشاء المنشور وربطه بالمستخدم
        post.setUser(user);
        Post newPost = postService.createPost(post);

        // تحويل المنشور إلى PostDto
        PostDto newPostDto = new PostDto(newPost.getId(), newPost.getCaption(), newPost.getImageUrl());
        return ResponseEntity.ok(newPostDto);
    }

    // عرض جميع منشورات مستخدم معين
    @GetMapping("/{username}") //I have to add paging here!
    public ResponseEntity<List<PostDto>> getUserPosts(@PathVariable String username , @RequestParam(defaultValue = "0") int page,  // الصفحة الافتراضية 0
                                                      @RequestParam(defaultValue = "10") int size ) {
        User user = userService.getUserByUsername(username);
        if (user == null) {
            throw new ResourceNotFoundException("User not found with username: " + username);
        }

            Pageable pageable = PageRequest.of(page, size);  // إنشاء كائن Pageable
            Page<Post> postsPage = postService.getPostsByUser(user, pageable);
            List<PostDto> postDTOs = postsPage.getContent().stream()
                .map(post -> new PostDto(post.getId(), post.getCaption(), post.getImageUrl()))
                .toList();
            return ResponseEntity.ok(postDTOs);
        }
    @GetMapping("/search")
    public ResponseEntity<Page<PostDto>> searchPosts(@RequestParam("keyword") String keyword, Pageable pageable) {
        Page<PostDto> posts = postService.searchPosts(keyword, pageable);
        return ResponseEntity.ok(posts);
    }
}
