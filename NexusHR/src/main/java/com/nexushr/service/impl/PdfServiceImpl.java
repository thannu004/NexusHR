package com.nexushr.service.impl;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lowagie.text.Document;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;
import com.nexushr.entity.Employee;
import com.nexushr.entity.Payroll;
import com.nexushr.exception.ResourceNotFoundException;
import com.nexushr.repository.EmployeeRepository;
import com.nexushr.repository.PayrollRepository;
import com.nexushr.service.PdfService;

@Service
public class PdfServiceImpl implements PdfService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private PayrollRepository payrollRepository;

    @Override
    public ByteArrayInputStream generateEmployeePdf(Long employeeId) {

        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Employee not found"));

        Document document = new Document();

        ByteArrayOutputStream out = new ByteArrayOutputStream();

        try {

            PdfWriter.getInstance(document, out);

            document.open();

            document.add(new Paragraph("NexusHR Employee Details"));
            document.add(new Paragraph(" "));
            document.add(new Paragraph("Employee ID : " + employee.getEmployeeId()));
            document.add(new Paragraph("Name : " + employee.getFirstName() + " " + employee.getLastName()));
            document.add(new Paragraph("Email : " + employee.getEmail()));
            document.add(new Paragraph("Department : " + employee.getDepartment().getDepartmentName()));
            document.add(new Paragraph("Designation : " + employee.getDesignation()));
            document.add(new Paragraph("Salary : ₹" + employee.getSalary()));

            document.close();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return new ByteArrayInputStream(out.toByteArray());
    }

    @Override
    public ByteArrayInputStream generatePayslipPdf(Long payrollId) {

        Payroll payroll = payrollRepository.findById(payrollId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Payroll not found"));

        Document document = new Document();

        ByteArrayOutputStream out = new ByteArrayOutputStream();

        try {

            PdfWriter.getInstance(document, out);

            document.open();

            document.add(new Paragraph("NexusHR Payslip"));
            document.add(new Paragraph(" "));
            document.add(new Paragraph("Employee : "
                    + payroll.getEmployee().getFirstName()
                    + " "
                    + payroll.getEmployee().getLastName()));

            document.add(new Paragraph("Month : " + payroll.getMonth()));
            document.add(new Paragraph("Year : " + payroll.getYear()));
            document.add(new Paragraph("Basic Salary : ₹" + payroll.getBasicSalary()));
            document.add(new Paragraph("Net Salary : ₹" + payroll.getNetSalary()));

            document.close();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return new ByteArrayInputStream(out.toByteArray());
    }

}