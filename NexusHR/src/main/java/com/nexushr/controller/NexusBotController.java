package com.nexushr.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/nexusbot")
public class NexusBotController {

    @PostMapping
    public Map<String, String> askBot(
            @RequestBody Map<String, String> request) {

        String question = request.get("question").toLowerCase();

        String answer;

        if (question.contains("leave")) {

            answer = "Apply leave from the Leave Module. HR/Admin will review your request.";

        } else if (question.contains("salary")
                || question.contains("payroll")) {

            answer = "Payroll is generated based on attendance and approved leave.";

        } else if (question.contains("attendance")) {

            answer = "Attendance is calculated using Check-In and Check-Out timings.";

        } else if (question.contains("department")) {

            answer = "Contact your HR for department-related changes.";

        } else {

            answer = "Sorry, I couldn't understand your question.";

        }

        Map<String, String> response = new HashMap<>();
        response.put("response", answer);

        return response;
    }

}