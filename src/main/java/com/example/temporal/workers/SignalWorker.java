package com.example.temporal.workers;

import com.example.temporal.activities.impl.BureauFeatureActivityImpl;
import com.example.temporal.activities.impl.BureauReportActivityImpl;
import com.example.temporal.external.BureauFeatureService;
import com.example.temporal.external.BureauService;
import com.example.temporal.utils.Utils;
import com.example.temporal.workflows.impl.SignalWorkflowImpl;
import io.temporal.worker.Worker;
import io.temporal.worker.WorkerFactory;
import io.temporal.worker.WorkflowImplementationOptions;

/**
 * Author - lakshya.jain <br>
 * Date - 11/07/2023
 */

public class SignalWorker {

    public static void main() {
        WorkflowImplementationOptions workflowImplementationOptions =
                WorkflowImplementationOptions.newBuilder().build();

        WorkerFactory workerFactory = WorkerFactory.newInstance(Utils.client);
        Worker worker = workerFactory.newWorker(Utils.signalQueue);

        worker.registerWorkflowImplementationTypes(workflowImplementationOptions, SignalWorkflowImpl.class);
        worker.registerActivitiesImplementations(new BureauReportActivityImpl(new BureauService()), new BureauFeatureActivityImpl(new BureauFeatureService()));

        workerFactory.start();
    }

}
