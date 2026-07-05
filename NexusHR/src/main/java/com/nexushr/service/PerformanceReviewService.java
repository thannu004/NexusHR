package com.nexushr.service;

import java.util.List;

import com.nexushr.entity.PerformanceReview;

import com.nexushr.dto.PerformanceReviewRequest;

public interface PerformanceReviewService {

	PerformanceReview addReview(PerformanceReviewRequest request);

    List<PerformanceReview> getAllReviews();

    List<PerformanceReview> getReviewsByEmployee(Long employeeId);

}