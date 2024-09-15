package com.instagram.clone.repository;

import com.instagram.clone.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    User findByUsername(String username);

    List<User> findByUsernameContainingOrEmailContaining(String username, String email);
}
