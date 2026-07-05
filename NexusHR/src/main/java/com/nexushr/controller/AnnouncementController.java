package com.nexushr.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.nexushr.entity.Announcement;
import com.nexushr.repository.AnnouncementRepository;

@RestController
@RequestMapping("/api/announcements")
public class AnnouncementController {

    @Autowired
    private AnnouncementRepository repository;

    @GetMapping
    public List<Announcement> getAll() {

        return repository.findAll();

    }

    @PostMapping
    public Announcement add(
            @RequestBody Announcement announcement) {

        announcement.setPostedDate(LocalDate.now());

        return repository.save(announcement);

    }

}