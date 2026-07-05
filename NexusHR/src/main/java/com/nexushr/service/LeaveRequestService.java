package com.nexushr.service;

import java.util.List;

import com.nexushr.entity.LeaveRequest;

import com.nexushr.dto.LeaveRequestDto;

public interface LeaveRequestService {

    LeaveRequest applyLeave(LeaveRequestDto request);

    LeaveRequest approveLeave(Long leaveId);

    LeaveRequest rejectLeave(Long leaveId);

    List<LeaveRequest> getAllLeaves();

    List<LeaveRequest> getEmployeeLeaves(Long employeeId);

}