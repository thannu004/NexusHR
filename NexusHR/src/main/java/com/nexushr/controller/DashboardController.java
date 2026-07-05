package com.nexushr.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.nexushr.dto.DashboardResponse;
import com.nexushr.repository.AttendanceRepository;
import com.nexushr.repository.DepartmentRepository;
import com.nexushr.repository.EmployeeRepository;
import com.nexushr.repository.LeaveRequestRepository;
import com.nexushr.repository.PayrollRepository;

@RestController
@RequestMapping("/api/dashboard")
public class DashboardController {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private AttendanceRepository attendanceRepository;

    @Autowired
    private LeaveRequestRepository leaveRequestRepository;
    
    @Autowired
    private PayrollRepository payrollRepository;
    
    @GetMapping
    public DashboardResponse dashboard() {

        DashboardResponse response = new DashboardResponse();

        response.setTotalEmployees(employeeRepository.count());
        response.setTotalDepartments(departmentRepository.count());
        response.setTotalAttendance(attendanceRepository.count());
        response.setPendingLeaveRequests(
                leaveRequestRepository.findAll()
                        .stream()
                        .filter(l -> "PENDING".equals(l.getStatus()))
                        .count());

        response.setEmployeesOnLeave(
                leaveRequestRepository.findAll()
                        .stream()
                        .filter(l -> "APPROVED".equals(l.getStatus()))
                        .count());

        response.setPayrollGenerated(
                payrollRepository.count());

        response.setPresentToday(
                attendanceRepository.findAll()
                        .stream()
                        .filter(a -> "FULL_DAY".equals(a.getAttendanceType())
                                || "HALF_DAY".equals(a.getAttendanceType()))
                        .count());

        return response;
    }

}