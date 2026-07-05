package com.nexushr.service.impl;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nexushr.entity.Attendance;
import com.nexushr.entity.Employee;
import com.nexushr.exception.ResourceNotFoundException;
import com.nexushr.repository.AttendanceRepository;
import com.nexushr.repository.EmployeeRepository;
import com.nexushr.service.AttendanceService;
import com.nexushr.repository.LeaveRequestRepository;

@Service
public class AttendanceServiceImpl implements AttendanceService {

    private static final int OFFICE_START_HOUR = 9;

    @Autowired
    private AttendanceRepository attendanceRepository;

    @Autowired
    private EmployeeRepository employeeRepository;
    
    @Autowired
    private LeaveRequestRepository leaveRequestRepository;

    @Override
    public Attendance checkIn(String email) {

    	Employee employee = employeeRepository.findByEmail(email)
    	        .orElseThrow(() ->
    	                new ResourceNotFoundException("Employee not found"));

    	Long employeeId = employee.getId();
    	
        attendanceRepository
        .findByEmployeeIdAndAttendanceDate(
                employeeId,
                LocalDate.now())
        .ifPresent(a -> {
            throw new RuntimeException(
                    "Employee already checked in today");
        });
        Attendance attendance = new Attendance();

        attendance.setEmployee(employee);
        attendance.setAttendanceDate(LocalDate.now());
        attendance.setCheckIn(LocalDateTime.now().toLocalTime());

        attendance.setWorkingHours(0);

        attendance.setAttendanceType("IN_PROGRESS");

        attendance.setRemarks("Checked In");

        if (attendance.getCheckIn().getHour() > OFFICE_START_HOUR
                || (attendance.getCheckIn().getHour() == OFFICE_START_HOUR
                && attendance.getCheckIn().getMinute() > 0)) {

            attendance.setLateEntry(true);

        } else {

            attendance.setLateEntry(false);

        }

        return attendanceRepository.save(attendance);
    }

    @Override
    public Attendance checkOut(String email) {

    	Employee employee = employeeRepository.findByEmail(email)
    	        .orElseThrow(() ->
    	                new ResourceNotFoundException("Employee not found"));

    	Attendance attendance =
    	attendanceRepository
    	.findTopByEmployeeIdAndCheckOutIsNullOrderByIdDesc(
    	        employee.getId())
    	.orElseThrow(() ->
    	        new ResourceNotFoundException(
    	                "No active attendance found"));

        attendance.setCheckOut(LocalDateTime.now().toLocalTime());
        
        if (attendance.getCheckOut().isBefore(attendance.getCheckIn())) {
            throw new RuntimeException("Invalid checkout time");
        }

        double hours = Duration.between(
                attendance.getCheckIn(),
                attendance.getCheckOut()).toMinutes() / 60.0;

        attendance.setWorkingHours(hours);

        boolean approvedLeave = leaveRequestRepository
                .findByEmployeeIdAndStartDateLessThanEqualAndEndDateGreaterThanEqualAndStatus(
                        attendance.getEmployee().getId(),
                        attendance.getAttendanceDate(),
                        attendance.getAttendanceDate(),
                        "APPROVED")
                .isPresent();

        if (approvedLeave) {

            attendance.setAttendanceType("LEAVE");
            attendance.setRemarks("Approved Leave");

        } else if (hours < 3) {

            attendance.setAttendanceType("ABSENT");
            attendance.setRemarks("Less than 3 working hours");

        } else if (hours <= 5) {

            attendance.setAttendanceType("HALF_DAY");
            attendance.setRemarks("Half Day");

        } else {

            attendance.setAttendanceType("FULL_DAY");
        
            attendance.setRemarks("Present");

        } 

        return attendanceRepository.save(attendance);
    }

    @Override
    public List<Attendance> getAllAttendance() {
        return attendanceRepository.findAll();
    }

    @Override
    public List<Attendance> getAttendanceByEmployee(Long employeeId) {
        return attendanceRepository.findByEmployeeId(employeeId);
    }

}