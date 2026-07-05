package com.nexushr.service;

import java.util.List;

import com.nexushr.entity.Payroll;

import com.nexushr.dto.PayrollRequest;

public interface PayrollService {

	Payroll generatePayroll(PayrollRequest request);

    List<Payroll> getAllPayrolls();

    Payroll getPayrollById(Long id);

}