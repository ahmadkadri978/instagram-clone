package com.instagram.clone.service;

import com.instagram.clone.entity.Notification;
import com.instagram.clone.entity.User;
import com.instagram.clone.repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
@Service
public class NotificationService {
    private final NotificationRepository notificationRepository;

    @Autowired
    public NotificationService(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }
    public List<Notification> getUserNotifications(User user) {
        return notificationRepository.findByUserAndIsReadFalse(user);
    }
    public void createNotification(User user , String message){
        Notification notification = new Notification();
        notification.setUser(user);
        notification.setMessage(message);
        notification.setCreatedAt(new Date());
        notification.setRead(false);
        notificationRepository.save(notification);
    }
    public void markNotificationAsRead(Long notificationId) {
        Notification notification = notificationRepository.findById(notificationId).orElseThrow();
        notification.setRead(true);
        notificationRepository.save(notification);
    }
}
