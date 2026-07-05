package com.nexushr.service;

import java.util.List;

import com.nexushr.entity.Attendance;

public interface AttendanceService {

    Attendance checkIn(String email);

    Attendance checkOut(String email);

    List<Attendance> getAllAttendance();

    List<Attendance> getAttendanceByEmployee(Long employeeId);

}