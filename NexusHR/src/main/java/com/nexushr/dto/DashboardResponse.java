package com.nexushr.dto;

public class DashboardResponse {

    private long totalEmployees;
    private long totalDepartments;

    private long totalAttendance;
    private long presentToday;

    private long employeesOnLeave;
    private long pendingLeaveRequests;

    private long payrollGenerated;

    public DashboardResponse() {
    }

    public long getTotalEmployees() {
        return totalEmployees;
    }

    public void setTotalEmployees(long totalEmployees) {
        this.totalEmployees = totalEmployees;
    }

    public long getTotalDepartments() {
        return totalDepartments;
    }

    public void setTotalDepartments(long totalDepartments) {
        this.totalDepartments = totalDepartments;
    }

    public long getTotalAttendance() {
        return totalAttendance;
    }

    public void setTotalAttendance(long totalAttendance) {
        this.totalAttendance = totalAttendance;
    }

    public long getPresentToday() {
        return presentToday;
    }

    public void setPresentToday(long presentToday) {
        this.presentToday = presentToday;
    }

    public long getEmployeesOnLeave() {
        return employeesOnLeave;
    }

    public void setEmployeesOnLeave(long employeesOnLeave) {
        this.employeesOnLeave = employeesOnLeave;
    }

    public long getPendingLeaveRequests() {
        return pendingLeaveRequests;
    }

    public void setPendingLeaveRequests(long pendingLeaveRequests) {
        this.pendingLeaveRequests = pendingLeaveRequests;
    }

    public long getPayrollGenerated() {
        return payrollGenerated;
    }

    public void setPayrollGenerated(long payrollGenerated) {
        this.payrollGenerated = payrollGenerated;
    }
}