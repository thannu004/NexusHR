package com.nexushr.service;

import java.util.List;

import com.nexushr.entity.Notification;

public interface NotificationService {

    Notification createNotification(Notification notification);

    List<Notification> getAllNotifications();

    Notification getNotificationById(Long id);

    void deleteNotification(Long id);

}