package com.nexushr.service;

import java.io.ByteArrayInputStream;

public interface PdfService {

    ByteArrayInputStream generateEmployeePdf(Long employeeId);

    ByteArrayInputStream generatePayslipPdf(Long payrollId);

}