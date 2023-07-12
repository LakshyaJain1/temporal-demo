package com.example.temporal.service;

import com.example.temporal.dto.UserData;
import com.example.temporal.utils.Utils;
import com.example.temporal.workflows.BureauFeaturesWorkflow;
import com.example.temporal.workflows.SignalWorkflow;
import io.temporal.api.common.v1.WorkflowExecution;
import io.temporal.client.WorkflowClient;
import io.temporal.client.WorkflowOptions;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * Author - lakshya.jain <br>
 * Date - 04/07/2023
 */

@Service
@Slf4j
public class WorkflowService {

    public void startWorkflow(UserData userData) {
        BureauFeaturesWorkflow workflow = Utils.client.newWorkflowStub(
                BureauFeaturesWorkflow.class,
                WorkflowOptions.newBuilder()
                        //.setWorkflowId("BureauTestWorkflow") Don't set this
                        .setTaskQueue(Utils.taskQueue)
                        .build()
        );

        WorkflowExecution workflowExecution = WorkflowClient.start(workflow::executeWorkflow, userData);

        log.info("Started bureau features workflow with workflowId: {}, and run id: {}",
                workflowExecution.getWorkflowId(), workflowExecution.getRunId());
    }

    public void startSignalWorkflow() {
        SignalWorkflow workflow = Utils.client.newWorkflowStub(
                SignalWorkflow.class,
                WorkflowOptions.newBuilder()
                        .setTaskQueue(Utils.signalQueue)
                        .build()
        );

        WorkflowExecution workflowExecution = WorkflowClient.start(workflow::myWorkflow);

        log.info("Started signal workflow with workflowId: {}, and run id: {}",
                workflowExecution.getWorkflowId(), workflowExecution.getRunId());
    }

}
