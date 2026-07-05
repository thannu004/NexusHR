package com.nexushr.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.nexushr.entity.EmployeeDocument;
import com.nexushr.service.EmployeeDocumentService;

@RestController
@RequestMapping("/api/documents")
public class EmployeeDocumentController {

    @Autowired
    private EmployeeDocumentService employeeDocumentService;

    @PostMapping("/upload")
    public EmployeeDocument uploadDocument(

            @RequestParam Long employeeId,

            @RequestParam String documentType,

            @RequestParam MultipartFile file

    ) throws IOException {

        return employeeDocumentService.uploadDocument(
                employeeId,
                documentType,
                file);

    }

    @GetMapping("/{employeeId}")
    public List<EmployeeDocument> getEmployeeDocuments(
            @PathVariable Long employeeId) {

        return employeeDocumentService
                .getEmployeeDocuments(employeeId);

    }

}