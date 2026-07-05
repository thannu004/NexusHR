package com.nexushr.service;

public interface AuditLogService {

    void saveLog(String action, String performedBy);

}