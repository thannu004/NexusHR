package com.nexushr.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.nexushr.entity.LeaveRequest;
import com.nexushr.service.LeaveRequestService;
import com.nexushr.dto.LeaveRequestDto;

@RestController
@RequestMapping("/api/leaves")
public class LeaveRequestController {

    @Autowired
    private LeaveRequestService leaveRequestService;

    @PostMapping
    public LeaveRequest applyLeave(
            @RequestBody LeaveRequestDto request) {

        return leaveRequestService.applyLeave(request);
    }

    @PutMapping("/approve/{id}")
    public LeaveRequest approveLeave(
            @PathVariable Long id) {

        return leaveRequestService.approveLeave(id);
    }

    @PutMapping("/reject/{id}")
    public LeaveRequest rejectLeave(
            @PathVariable Long id) {

        return leaveRequestService.rejectLeave(id);
    }

    @GetMapping
    public List<LeaveRequest> getAllLeaves() {

        return leaveRequestService.getAllLeaves();
    }

    @GetMapping("/employee/{employeeId}")
    public List<LeaveRequest> getEmployeeLeaves(
            @PathVariable Long employeeId) {

        return leaveRequestService.getEmployeeLeaves(employeeId);
    }

}