package com.nexushr.service;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.nexushr.entity.EmployeeDocument;

public interface EmployeeDocumentService {

    EmployeeDocument uploadDocument(
            Long employeeId,
            String documentType,
            MultipartFile file) throws IOException;

    List<EmployeeDocument> getEmployeeDocuments(Long employeeId);

}