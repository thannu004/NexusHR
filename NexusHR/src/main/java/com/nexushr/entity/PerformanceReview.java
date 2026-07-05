package com.nexushr.entity;

import java.time.LocalDate;

import jakarta.persistence.*;

@Entity
@Table(name = "performance_reviews")
public class PerformanceReview {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

    @Column(nullable = false)
    private String reviewPeriod;

    @Column(nullable = false)
    private double rating;

    @Column(nullable = false)
    private String remarks;

    @Column(nullable = false)
    private LocalDate reviewDate;

    public PerformanceReview() {
    }

    public PerformanceReview(Long id, Employee employee,
            String reviewPeriod, double rating,
            String remarks, LocalDate reviewDate) {

        this.id = id;
        this.employee = employee;
        this.reviewPeriod = reviewPeriod;
        this.rating = rating;
        this.remarks = remarks;
        this.reviewDate = reviewDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public String getReviewPeriod() {
        return reviewPeriod;
    }

    public void setReviewPeriod(String reviewPeriod) {
        this.reviewPeriod = reviewPeriod;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public LocalDate getReviewDate() {
        return reviewDate;
    }

    public void setReviewDate(LocalDate reviewDate) {
        this.reviewDate = reviewDate;
    }

}