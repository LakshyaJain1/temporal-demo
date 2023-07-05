package com.example.temporal.workers;

import com.example.temporal.activities.impl.BureauFeatureActivityImpl;
import com.example.temporal.activities.impl.BureauReportActivityImpl;
import com.example.temporal.external.BureauFeatureService;
import com.example.temporal.external.BureauService;
import com.example.temporal.utils.Utils;
import com.example.temporal.workflows.impl.BureauFeaturesWorkflowImpl;
import io.temporal.worker.Worker;
import io.temporal.worker.WorkerFactory;
import io.temporal.worker.WorkflowImplementationOptions;

public class BureauWorker {

    public static void main() {
        WorkflowImplementationOptions workflowImplementationOptions =
                WorkflowImplementationOptions.newBuilder()
                        .setFailWorkflowExceptionTypes(NullPointerException.class)
                        .build();

        WorkerFactory workerFactory = WorkerFactory.newInstance(Utils.client);
        Worker worker = workerFactory.newWorker(Utils.taskQueue);

        worker.registerWorkflowImplementationTypes(workflowImplementationOptions, BureauFeaturesWorkflowImpl.class);
        worker.registerActivitiesImplementations(new BureauReportActivityImpl(new BureauService()), new BureauFeatureActivityImpl(new BureauFeatureService()));

        workerFactory.start();
    }

}
