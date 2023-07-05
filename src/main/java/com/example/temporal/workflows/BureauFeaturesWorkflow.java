package com.example.temporal.workflows;

import com.example.temporal.dto.UserData;
import io.temporal.workflow.WorkflowInterface;
import io.temporal.workflow.WorkflowMethod;

/**
 * Author - lakshya.jain <br>
 * Date - 04/07/2023
 */

@WorkflowInterface
public interface BureauFeaturesWorkflow {

    @WorkflowMethod
    void executeWorkflow(UserData userData);

}
