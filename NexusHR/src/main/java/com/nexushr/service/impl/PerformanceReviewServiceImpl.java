package com.nexushr.service.impl;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nexushr.dto.PerformanceReviewRequest;
import com.nexushr.entity.Employee;
import com.nexushr.entity.PerformanceReview;
import com.nexushr.exception.ResourceNotFoundException;
import com.nexushr.repository.EmployeeRepository;
import com.nexushr.repository.PerformanceReviewRepository;
import com.nexushr.service.PerformanceReviewService;

@Service
public class PerformanceReviewServiceImpl
        implements PerformanceReviewService {

    @Autowired
    private PerformanceReviewRepository performanceReviewRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public PerformanceReview addReview(
            PerformanceReviewRequest request) {

        Employee employee = employeeRepository.findById(
                request.getEmployeeId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Employee not found"));

        PerformanceReview review = new PerformanceReview();

        review.setEmployee(employee);
        review.setReviewPeriod(request.getReviewPeriod());
        review.setRating(request.getRating());
        review.setRemarks(request.getRemarks());
        review.setReviewDate(LocalDate.now());

        return performanceReviewRepository.save(review);
    }

    @Override
    public List<PerformanceReview> getAllReviews() {
        return performanceReviewRepository.findAll();
    }

    @Override
    public List<PerformanceReview> getReviewsByEmployee(Long employeeId) {
        return performanceReviewRepository.findByEmployeeId(employeeId);
    }

}