package com.nexushr.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nexushr.dto.LeaveRequestDto;
import com.nexushr.entity.Employee;
import com.nexushr.entity.LeaveRequest;
import com.nexushr.entity.PersonalNotification;
import com.nexushr.exception.ResourceNotFoundException;
import com.nexushr.repository.EmployeeRepository;
import com.nexushr.repository.LeaveRequestRepository;
import com.nexushr.repository.PersonalNotificationRepository;
import com.nexushr.service.LeaveRequestService;
import com.nexushr.service.AuditLogService;

@Service
public class LeaveRequestServiceImpl
        implements LeaveRequestService {

    @Autowired
    private LeaveRequestRepository leaveRequestRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private PersonalNotificationRepository personalNotificationRepository;
    
    @Autowired
    private AuditLogService auditLogService;
    
    @Override
    public LeaveRequest applyLeave(LeaveRequestDto request) {

        Employee employee = employeeRepository.findById(
                request.getEmployeeId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Employee not found"));

        LeaveRequest leave = new LeaveRequest();

        leave.setEmployee(employee);
        leave.setLeaveType(request.getLeaveType());
        leave.setStartDate(request.getStartDate());
        leave.setEndDate(request.getEndDate());
        leave.setReason(request.getReason());
        leave.setStatus("PENDING");

        return leaveRequestRepository.save(leave);
    }

    @Override
    public LeaveRequest approveLeave(Long leaveId) {

        LeaveRequest leave = leaveRequestRepository.findById(leaveId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Leave request not found"));

        leave.setStatus("APPROVED");

        LeaveRequest savedLeave = leaveRequestRepository.save(leave);

        PersonalNotification notification = new PersonalNotification();

        notification.setEmployee(savedLeave.getEmployee());
        notification.setTitle("Leave Approved");
        notification.setMessage(
                "Your leave request from "
                        + savedLeave.getStartDate()
                        + " to "
                        + savedLeave.getEndDate()
                        + " has been approved.");

        notification.setRead(false);
        notification.setCreatedAt(LocalDateTime.now());

        personalNotificationRepository.save(notification);

        auditLogService.saveLog(
                "Leave Approved",
                savedLeave.getEmployee().getFirstName()
                        + " "
                        + savedLeave.getEmployee().getLastName());

        return savedLeave;
    }

    @Override
    public LeaveRequest rejectLeave(Long leaveId) {

        LeaveRequest leave = leaveRequestRepository.findById(leaveId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Leave request not found"));

        leave.setStatus("REJECTED");

        return leaveRequestRepository.save(leave);
    }

    @Override
    public List<LeaveRequest> getAllLeaves() {
        return leaveRequestRepository.findAll();
    }

    @Override
    public List<LeaveRequest> getEmployeeLeaves(Long employeeId) {
        return leaveRequestRepository.findByEmployeeId(employeeId);
    }

}