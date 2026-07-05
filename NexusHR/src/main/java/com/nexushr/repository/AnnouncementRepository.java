package com.nexushr.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.nexushr.entity.Announcement;

public interface AnnouncementRepository
        extends JpaRepository<Announcement, Long> {

}