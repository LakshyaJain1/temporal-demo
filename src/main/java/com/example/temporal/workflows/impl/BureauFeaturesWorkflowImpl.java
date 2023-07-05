package com.example.temporal.workflows.impl;

import com.example.temporal.activities.BureauFeatureActivity;
import com.example.temporal.activities.BureauReportActivity;
import com.example.temporal.dto.BureauReport;
import com.example.temporal.dto.ParsedBureauReport;
import com.example.temporal.dto.UserData;
import com.example.temporal.workflows.BureauFeaturesWorkflow;
import io.temporal.activity.ActivityOptions;
import io.temporal.common.RetryOptions;
import io.temporal.workflow.Workflow;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.Duration;

/**
 * Author - lakshya.jain <br>
 * Date - 04/07/2023
 */

//Don't add @Service here as Temporal will throw error

@Slf4j
public class BureauFeaturesWorkflowImpl implements BureauFeaturesWorkflow {

    private final BureauReportActivity bureauReportActivity;
    private final BureauFeatureActivity bureauFeatureActivity;

    public BureauFeaturesWorkflowImpl(){

        ActivityOptions activityOptions =
                ActivityOptions.newBuilder()
                        .setStartToCloseTimeout(Duration.ofSeconds(20))
                        .setRetryOptions(
                                RetryOptions.newBuilder()
                                        .setInitialInterval(Duration.ofSeconds(1))
                                        .setMaximumAttempts(4)
                                        .setDoNotRetry(IllegalArgumentException.class.getName())
                                        .build())
                        .build();

        this.bureauReportActivity = Workflow.newActivityStub(BureauReportActivity.class, activityOptions);
        this.bureauFeatureActivity = Workflow.newActivityStub(BureauFeatureActivity.class, activityOptions);
    }

    @Override
    public void executeWorkflow(UserData userData) {

        BureauReport bureauReport = bureauReportActivity.fetchBureauReport(userData);
        //Workflow.sleep(100000);
        ParsedBureauReport parsedBureauReport = bureauFeatureActivity.getParsedBureauReport(bureauReport);
        log.info("Final Response: {}", parsedBureauReport);

    }
}
