package com.nexushr.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.nexushr.entity.Attendance;
import com.nexushr.service.AttendanceService;

@RestController
@RequestMapping("/api/attendance")
public class AttendanceController {

    @Autowired
    private AttendanceService attendanceService;

    @PostMapping("/checkin")
    public Attendance checkIn(Principal principal) {

        return attendanceService.checkIn(principal.getName());

    }

    @PutMapping("/checkout")
    public Attendance checkOut(Principal principal) {

        return attendanceService.checkOut(principal.getName());

    }

    @GetMapping
    public List<Attendance> getAllAttendance() {

        return attendanceService.getAllAttendance();

    }

    @GetMapping("/employee/{employeeId}")
    public List<Attendance> getAttendanceByEmployee(
            @PathVariable Long employeeId) {

        return attendanceService.getAttendanceByEmployee(employeeId);

    }

}