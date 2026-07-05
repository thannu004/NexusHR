package com.nexushr.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.nexushr.entity.PersonalNotification;
import com.nexushr.service.PersonalNotificationService;

@RestController
@RequestMapping("/api/personal-notifications")
public class PersonalNotificationController {

    @Autowired
    private PersonalNotificationService service;

    @PostMapping
    public PersonalNotification createNotification(
            @RequestBody PersonalNotification notification) {

        return service.createNotification(notification);
    }

    @GetMapping("/employee/{employeeId}")
    public List<PersonalNotification> getEmployeeNotifications(
            @PathVariable Long employeeId) {

        return service.getEmployeeNotifications(employeeId);
    }

    @PutMapping("/read/{notificationId}")
    public PersonalNotification markAsRead(
            @PathVariable Long notificationId) {

        return service.markAsRead(notificationId);
    }

}