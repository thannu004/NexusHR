package com.nexushr.service.impl;

import java.io.IOException;
import java.util.Map;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.nexushr.entity.Employee;
import com.nexushr.entity.EmployeeDocument;
import com.nexushr.repository.EmployeeDocumentRepository;
import com.nexushr.repository.EmployeeRepository;
import com.nexushr.service.EmployeeDocumentService;

@Service
public class EmployeeDocumentServiceImpl implements EmployeeDocumentService {

    @Autowired
    private Cloudinary cloudinary;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private EmployeeDocumentRepository repository;

    @Override
    public EmployeeDocument uploadDocument(
            Long employeeId,
            String documentType,
            MultipartFile file) throws IOException {

        Employee employee = employeeRepository
                .findById(employeeId)
                .orElseThrow();

        Map<?, ?> uploadResult =
                cloudinary.uploader().upload(
                        file.getBytes(),
                        ObjectUtils.asMap(
                                "resource_type", "raw"
                        ));

        EmployeeDocument document = new EmployeeDocument();

        document.setEmployee(employee);
        document.setDocumentType(documentType);
        document.setFileName(file.getOriginalFilename());
        document.setFileUrl(uploadResult.get("secure_url").toString());

        return repository.save(document);

    }

    @Override
    public List<EmployeeDocument> getEmployeeDocuments(Long employeeId) {

        return repository.findByEmployeeId(employeeId);

    }

}