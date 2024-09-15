package com.instagram.clone.service;

import com.instagram.clone.entity.Follow;
import com.instagram.clone.entity.User;
import com.instagram.clone.error.ResourceNotFoundException;
import com.instagram.clone.repository.FollowRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FollowService {

    private final FollowRepository followRepository;
    private final UserService userService; // لفحص وجود المستخدم

    public FollowService(FollowRepository followRepository, UserService userService) {
        this.followRepository = followRepository;
        this.userService = userService;
    }

    // متابعة مستخدم
    @Transactional
    public void followUser(String followerUsername, String followingUsername) {
        User follower = userService.getUserByUsername(followerUsername);
        User following = userService.getUserByUsername(followingUsername);

        if (follower != null && following != null && !follower.equals(following)) {
            if (!followRepository.existsByFollowerAndFollowing(follower, following)) {
                Follow follow = new Follow(follower, following);
                followRepository.save(follow);
            }
        }
    }

    // إلغاء متابعة مستخدم
    @Transactional
    public void unfollowUser(String followerUsername, String followingUsername) {
        User follower = userService.getUserByUsername(followerUsername);
        User following = userService.getUserByUsername(followingUsername);

        if (follower != null && following != null) {
            followRepository.deleteByFollowerAndFollowing(follower, following);
        }
    }

    // الحصول على قائمة المتابعين
    public List<User> getFollowers(String username) {
        User user = userService.getUserByUsername(username);
        if (user == null) {
            throw new ResourceNotFoundException("User with username " + username + " not found.");
        }
            return followRepository.findByFollowing(user).stream()
                    .map(Follow::getFollower)
                    .collect(Collectors.toList());
    }

    // الحصول على قائمة الذين يتابعهم المستخدم
    public List<User> getFollowing(String username) {
        User user = userService.getUserByUsername(username);
        if (user == null) {
            throw new ResourceNotFoundException("User with username " + username + " not found.");
        }

            return followRepository.findByFollower(user).stream()
                    .map(Follow::getFollowing)
                    .collect(Collectors.toList());
        }
}

