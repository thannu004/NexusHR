package com.nexushr.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nexushr.entity.Payroll;

public interface PayrollRepository extends JpaRepository<Payroll, Long> {

	Optional<Payroll> findByEmployeeIdAndMonthAndYear(
	        Long employeeId,
	        String month,
	        int year);

}