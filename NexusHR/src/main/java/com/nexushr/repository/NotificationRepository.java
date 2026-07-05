package com.nexushr.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nexushr.entity.Notification;

public interface NotificationRepository
        extends JpaRepository<Notification, Long> {

}