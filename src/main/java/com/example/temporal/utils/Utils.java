package com.example.temporal.utils;

import io.temporal.client.WorkflowClient;
import io.temporal.serviceclient.WorkflowServiceStubs;

/**
 * Author - lakshya.jain <br>
 * Date - 04/07/2023
 */

public class Utils {

    public static final WorkflowServiceStubs service = WorkflowServiceStubs.newLocalServiceStubs();
    public static final WorkflowClient client = WorkflowClient.newInstance(service);
    public static final String taskQueue = "BureauTask";
}
