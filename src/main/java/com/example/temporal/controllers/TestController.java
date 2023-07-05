package com.example.temporal.controllers;

import com.example.temporal.dto.UserData;
import com.example.temporal.service.WorkflowService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Author - lakshya.jain <br>
 * Date - 04/07/2023
 */

@RestController
@AllArgsConstructor
public class TestController {

    private final WorkflowService workflowService;

    @PostMapping("/temp")
    public void getParsedBureauReport(@RequestBody UserData userData) {
        workflowService.startWorkflow(userData);
    }
}
