package com.example.temporal.workflows;

import io.temporal.workflow.QueryMethod;
import io.temporal.workflow.SignalMethod;
import io.temporal.workflow.WorkflowInterface;
import io.temporal.workflow.WorkflowMethod;

@WorkflowInterface
public interface SignalWorkflow {

    @WorkflowMethod
    void myWorkflow();

    @SignalMethod
    void submitUserDetails(String userDetails);

    @SignalMethod
    void resetUserDetails();

    @SignalMethod
    void signalActivity1(String activity1);

    @SignalMethod
    void signalActivity2();

    @QueryMethod
    String getWorkflowStatus();

}