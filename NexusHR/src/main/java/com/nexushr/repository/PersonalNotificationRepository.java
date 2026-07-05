package com.nexushr.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nexushr.entity.PersonalNotification;

public interface PersonalNotificationRepository
        extends JpaRepository<PersonalNotification, Long> {

    List<PersonalNotification> findByEmployeeId(Long employeeId);

}