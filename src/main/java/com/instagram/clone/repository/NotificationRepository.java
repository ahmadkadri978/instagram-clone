package com.instagram.clone.repository;

import com.instagram.clone.entity.Notification;
import com.instagram.clone.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotificationRepository extends JpaRepository<Notification , Long> {
    List<Notification> findByUserAndIsReadFalse(User user);
}
