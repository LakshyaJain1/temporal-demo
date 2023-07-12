package com.example.temporal.workflows.impl;

import com.example.temporal.activities.BureauFeatureActivity;
import com.example.temporal.activities.BureauReportActivity;
import com.example.temporal.workflows.SignalWorkflow;
import io.temporal.activity.ActivityOptions;
import io.temporal.common.RetryOptions;
import io.temporal.workflow.Workflow;
import lombok.extern.log4j.Log4j2;

import java.time.Duration;

/**
 * Author - lakshya.jain <br>
 * Date - 10/07/2023
 */


@Log4j2
public class SignalWorkflowImpl implements SignalWorkflow {

    private String userSubmittedDetails;
    private String userActivity1;
    private String state;
    private final BureauReportActivity bureauReportActivity;
    private final BureauFeatureActivity bureauFeatureActivity;

    public SignalWorkflowImpl() {

        ActivityOptions activityOptions =
                ActivityOptions.newBuilder()
                        .setStartToCloseTimeout(Duration.ofSeconds(2000))
                        .setRetryOptions(
                                RetryOptions.newBuilder()
                                        .setInitialInterval(Duration.ofSeconds(1))
                                        .setMaximumAttempts(40)
                                        .setDoNotRetry(IllegalArgumentException.class.getName())
                                        .build())
                        .build();

        this.bureauReportActivity = Workflow.newActivityStub(BureauReportActivity.class, activityOptions);
        this.bureauFeatureActivity = Workflow.newActivityStub(BureauFeatureActivity.class, activityOptions);
    }

    @Override
    public void myWorkflow() {

        log.info("Start signal workflow...");
        log.info("Lakshya userSubmittedDetails before {}", userSubmittedDetails);

        Workflow.await(() -> userSubmittedDetails != null);

        log.info("Lakshya userSubmittedDetails after {}", userSubmittedDetails);
        log.info("Lakshya userActivity1 before {}", userActivity1);

        Workflow.await(() -> userActivity1 != null);

        log.info("Lakshya userActivity1 after {}", userActivity1);
        log.info("Proceed...");
    }

    @Override
    public void submitUserDetails(String userDetails) {
        //Call user platform service to store the user details and all.
        try {
            state = "User details not submitted";
            userSubmittedDetails = bureauReportActivity.fetchBureauReport(null).getName();
            state = String.format("User details Submitted %s, Activity 1 not submitted", userSubmittedDetails);
        } finally {
            Workflow.await(() -> false);
        }
    }

    @Override
    public void resetUserDetails() {
        //Call user platform service to store the user details and all.
        try {
            state = "User details not submitted";
        } finally {
            Workflow.await(() -> false);
        }
    }

    @Override
    public void signalActivity1(String activity1) {
        try {
            userActivity1 = bureauFeatureActivity.getParsedBureauReport(null).getName();
            state = String.format("Activity 1 submitted %s", userActivity1);
        } finally {
            Workflow.await(() -> false);
        }

    }

    @Override
    public void signalActivity2() {
    }

    @Override
    public String getWorkflowStatus() {
        log.info("Lakshya State: {}", state);
        return state;
    }
}
