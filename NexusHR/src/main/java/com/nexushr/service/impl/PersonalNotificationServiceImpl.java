package com.nexushr.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nexushr.entity.PersonalNotification;
import com.nexushr.exception.ResourceNotFoundException;
import com.nexushr.repository.PersonalNotificationRepository;
import com.nexushr.service.PersonalNotificationService;

@Service
public class PersonalNotificationServiceImpl
        implements PersonalNotificationService {

    @Autowired
    private PersonalNotificationRepository repository;

    @Override
    public PersonalNotification createNotification(
            PersonalNotification notification) {

        notification.setCreatedAt(LocalDateTime.now());
        notification.setRead(false);

        return repository.save(notification);
    }

    @Override
    public List<PersonalNotification> getEmployeeNotifications(
            Long employeeId) {

        return repository.findByEmployeeId(employeeId);
    }

    @Override
    public PersonalNotification markAsRead(Long notificationId) {

        PersonalNotification notification = repository.findById(notificationId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Notification not found"));

        notification.setRead(true);

        return repository.save(notification);
    }

}