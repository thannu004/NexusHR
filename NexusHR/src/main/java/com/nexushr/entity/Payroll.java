package com.nexushr.entity;

import java.time.LocalDate;

import jakarta.persistence.*;

@Entity
@Table(name = "payroll")
public class Payroll {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

    @Column(nullable = false)
    private String month;

    @Column(nullable = false)
    private int year;

    @Column(nullable = false)
    private int totalWorkingDays;

    @Column(nullable = false)
    private double payableDays;

    @Column(nullable = false)
    private double basicSalary;

    @Column(nullable = false)
    private double netSalary;

    @Column(nullable = false)
    private LocalDate generatedDate;

    public Payroll() {
    }

    public Payroll(Long id, Employee employee, String month, int year,
            int totalWorkingDays, double payableDays,
            double basicSalary, double netSalary,
            LocalDate generatedDate) {

        this.id = id;
        this.employee = employee;
        this.month = month;
        this.year = year;
        this.totalWorkingDays = totalWorkingDays;
        this.payableDays = payableDays;
        this.basicSalary = basicSalary;
        this.netSalary = netSalary;
        this.generatedDate = generatedDate;
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

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getTotalWorkingDays() {
        return totalWorkingDays;
    }

    public void setTotalWorkingDays(int totalWorkingDays) {
        this.totalWorkingDays = totalWorkingDays;
    }

    public double getPayableDays() {
        return payableDays;
    }

    public void setPayableDays(double payableDays) {
        this.payableDays = payableDays;
    }

    public double getBasicSalary() {
        return basicSalary;
    }

    public void setBasicSalary(double basicSalary) {
        this.basicSalary = basicSalary;
    }

    public double getNetSalary() {
        return netSalary;
    }

    public void setNetSalary(double netSalary) {
        this.netSalary = netSalary;
    }

    public LocalDate getGeneratedDate() {
        return generatedDate;
    }

    public void setGeneratedDate(LocalDate generatedDate) {
        this.generatedDate = generatedDate;
    }

}