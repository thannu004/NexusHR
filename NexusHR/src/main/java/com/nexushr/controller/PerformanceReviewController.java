package com.nexushr.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.nexushr.entity.PerformanceReview;
import com.nexushr.service.PerformanceReviewService;
import com.nexushr.dto.PerformanceReviewRequest;

@RestController
@RequestMapping("/api/performance")
public class PerformanceReviewController {

    @Autowired
    private PerformanceReviewService performanceReviewService;

    @PostMapping
    public PerformanceReview addReview(
            @RequestBody PerformanceReviewRequest request) {

        return performanceReviewService.addReview(request);
    }

    @GetMapping
    public List<PerformanceReview> getAllReviews() {

        return performanceReviewService.getAllReviews();
    }

    @GetMapping("/employee/{employeeId}")
    public List<PerformanceReview> getReviewsByEmployee(
            @PathVariable Long employeeId) {

        return performanceReviewService
                .getReviewsByEmployee(employeeId);
    }

}