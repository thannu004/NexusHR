package com.nexushr.service;

import java.util.List;

import com.nexushr.entity.PersonalNotification;

public interface PersonalNotificationService {

    PersonalNotification createNotification(PersonalNotification notification);

    List<PersonalNotification> getEmployeeNotifications(Long employeeId);

    PersonalNotification markAsRead(Long notificationId);

}