package com.example.temporal.controllers;

import com.example.temporal.dto.UserData;
import com.example.temporal.service.WorkflowService;
import com.example.temporal.utils.Utils;
import com.example.temporal.workflows.SignalWorkflow;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    @PostMapping("/temp1")
    public void startSignalWorkflow() {
        workflowService.startSignalWorkflow();
    }

    @PostMapping("/submitUserDetails/{workflowId}")
    public ResponseEntity<String> submitUserDetails(
            @PathVariable String workflowId,
            @RequestBody String userDetails) {
        SignalWorkflow workflow = Utils.client.newWorkflowStub(SignalWorkflow.class, workflowId);
        workflow.submitUserDetails(userDetails);
        return ResponseEntity.ok("User details submitted for workflow: " + workflowId);
    }

    @PostMapping("/resetUserDetails/{workflowId}")
    public ResponseEntity<String> resetUserDetails(
            @PathVariable String workflowId) {
        SignalWorkflow workflow = Utils.client.newWorkflowStub(SignalWorkflow.class, workflowId);
        workflow.resetUserDetails();
        return ResponseEntity.ok("User details reset for workflow: " + workflowId);
    }

    @PostMapping("/submitActivity1/{workflowId}")
    public ResponseEntity<String> submitActivity1(
            @PathVariable String workflowId,
            @RequestBody String activity1) {
        SignalWorkflow workflow = Utils.client.newWorkflowStub(SignalWorkflow.class, workflowId);
        workflow.signalActivity1(activity1);
        return ResponseEntity.ok("Activity 1 submitted for workflow: " + workflowId);
    }

    @GetMapping("/status/{workflowId}")
    public ResponseEntity<String> getWorkflowStatus(@PathVariable String workflowId) {
        SignalWorkflow workflow = Utils.client.newWorkflowStub(SignalWorkflow.class, workflowId);
        String status = workflow.getWorkflowStatus();
        return ResponseEntity.ok("Status for workflow: " + workflowId + " - " + status);
    }

}
