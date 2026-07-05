package com.nexushr.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ManagerController {

    @GetMapping("/api/manager/dashboard")
    public String managerDashboard() {
        return "Welcome Manager";
    }

}