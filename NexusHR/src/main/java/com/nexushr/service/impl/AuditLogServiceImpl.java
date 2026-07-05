package com.nexushr.service.impl;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nexushr.entity.AuditLog;
import com.nexushr.repository.AuditLogRepository;
import com.nexushr.service.AuditLogService;

@Service
public class AuditLogServiceImpl implements AuditLogService {

    @Autowired
    private AuditLogRepository auditLogRepository;

    @Override
    public void saveLog(String action, String performedBy) {

        AuditLog log = new AuditLog();

        log.setAction(action);
        log.setPerformedBy(performedBy);
        log.setActionTime(LocalDateTime.now());

        auditLogRepository.save(log);
    }
}