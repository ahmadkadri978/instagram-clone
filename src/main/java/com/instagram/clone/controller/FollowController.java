package com.instagram.clone.controller;

import com.instagram.clone.dto.UserDto;
import com.instagram.clone.entity.Follow;
import com.instagram.clone.entity.User;
import com.instagram.clone.mapper.UserMapper;
import com.instagram.clone.service.FollowService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.stream.Collectors;



@RestController
@RequestMapping("/api/follows")
public class FollowController {

    private final FollowService followService;
    private final UserMapper userMapper = new UserMapper();

    public FollowController(FollowService followService) {
        this.followService = followService;
    }

    // متابعة مستخدم
    @PostMapping("/{followerUsername}/follow/{followingUsername}")
    public ResponseEntity<String> followUser(
            @PathVariable String followerUsername,
            @PathVariable String followingUsername) {
        followService.followUser(followerUsername, followingUsername);
        return ResponseEntity.ok("You are now following " + followingUsername);
    }

    // إلغاء متابعة مستخدم
    @DeleteMapping("/{followerUsername}/unfollow/{followingUsername}")
    public ResponseEntity<String> unfollowUser(
            @PathVariable String followerUsername,
            @PathVariable String followingUsername) {
        followService.unfollowUser(followerUsername, followingUsername);
        return ResponseEntity.ok("You have unfollowed " + followingUsername);
    }

    // استرجاع المتابعين لمستخدم معين
    @GetMapping("/{username}/followers")
    public ResponseEntity<List<UserDto>> getFollowers(@PathVariable String username) {
        List<User> followers = followService.getFollowers(username);

        List<UserDto> followersDto = followers.stream()
                .map(userMapper::toUserDto)
                .collect(Collectors.toList());

            return ResponseEntity.ok(followersDto);
    }

    // استرجاع الذين يتابعهم المستخدم
    @GetMapping("/{username}/following")
    public ResponseEntity<List<UserDto>> getFollowing(@PathVariable String username) {
        List<User> following = followService.getFollowing(username);
        List<UserDto> followingDto = following.stream()
                .map(userMapper::toUserDto)
                .collect(Collectors.toList());

            return ResponseEntity.ok(followingDto);
    }
}
