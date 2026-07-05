package com.nexushr.service.impl;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nexushr.dto.PayrollRequest;
import com.nexushr.entity.Attendance;
import com.nexushr.entity.Employee;
import com.nexushr.entity.Payroll;
import com.nexushr.exception.ResourceNotFoundException;
import com.nexushr.repository.AttendanceRepository;
import com.nexushr.repository.EmployeeRepository;
import com.nexushr.repository.PayrollRepository;
import com.nexushr.service.PayrollService;
import com.nexushr.entity.PersonalNotification;
import com.nexushr.repository.PersonalNotificationRepository;
import java.time.LocalDateTime;
import com.nexushr.service.AuditLogService;

@Service
public class PayrollServiceImpl implements PayrollService {

    @Autowired
    private PayrollRepository payrollRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private AttendanceRepository attendanceRepository;
    
    @Autowired
    private PersonalNotificationRepository personalNotificationRepository;
    
    @Autowired
    private AuditLogService auditLogService;
    
    @Override
    public Payroll generatePayroll(PayrollRequest request) {

        Employee employee = employeeRepository.findById(request.getEmployeeId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Employee not found"));

        String month = LocalDate.now().getMonth().name();
        int year = LocalDate.now().getYear();

        payrollRepository
                .findByEmployeeIdAndMonthAndYear(
                        employee.getId(),
                        month,
                        year)
                .ifPresent(payroll -> {
                    throw new RuntimeException(
                            "Payroll already generated for this month");
                });

        List<Attendance> attendanceList =
                attendanceRepository.findByEmployeeId(employee.getId());

        double payableDays = 0;

        for (Attendance attendance : attendanceList) {

        	switch (attendance.getAttendanceType()) {

            case "FULL_DAY":
                payableDays += 1;
                break;

            case "HALF_DAY":
                payableDays += 0.5;
                break;

            case "LEAVE":
                payableDays += 1;
                break;

            default:
                break;
        }
        	
        }

        double basicSalary = employee.getSalary();

        int totalWorkingDays = request.getTotalWorkingDays();

        double netSalary =
                (basicSalary / totalWorkingDays) * payableDays;

        Payroll payroll = new Payroll();

        payroll.setEmployee(employee);
        payroll.setMonth(month);
        payroll.setYear(year);
        payroll.setTotalWorkingDays(totalWorkingDays);
        payroll.setPayableDays(payableDays);
        payroll.setBasicSalary(basicSalary);
        payroll.setNetSalary(netSalary);
        payroll.setGeneratedDate(LocalDate.now());

        Payroll savedPayroll = payrollRepository.save(payroll);

        PersonalNotification notification = new PersonalNotification();

        notification.setEmployee(employee);

        notification.setTitle("Salary Credited");

        notification.setMessage(
                "Your salary for "
                        + month
                        + " "
                        + year
                        + " has been generated.");

        notification.setRead(false);

        notification.setCreatedAt(LocalDateTime.now());

        personalNotificationRepository.save(notification);

        auditLogService.saveLog(
                "Payroll Generated",
                employee.getFirstName() + " " + employee.getLastName());

        return savedPayroll;
    }

    @Override
    public List<Payroll> getAllPayrolls() {
        return payrollRepository.findAll();
    }

    @Override
    public Payroll getPayrollById(Long id) {

        return payrollRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Payroll not found"));
    }

}