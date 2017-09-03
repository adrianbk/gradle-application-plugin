package com.adrianbk.deployment.taskgen;

import com.adrianbk.deployment.model.ApplicationEnvironment;
import com.adrianbk.deployment.model.Distribution;
import com.adrianbk.deployment.model.InfrastructureComponent;
import org.gradle.api.tasks.TaskContainer;

public class InfrastructureTaskGenerationSpec<D extends Distribution, C extends InfrastructureComponent> {

    private final ApplicationEnvironment applicationEnvironment;
    private final D distribution;
    private final C infrastructureComponent;
    private final TaskContainer taskContainer;

    public InfrastructureTaskGenerationSpec(D distribution, C infrastructureComponent, ApplicationEnvironment applicationEnvironment, TaskContainer taskContainer) {
        this.distribution = distribution;
        this.infrastructureComponent = infrastructureComponent;
        this.applicationEnvironment = applicationEnvironment;
        this.taskContainer = taskContainer;
    }

    public TaskContainer getTaskContainer() {
        return taskContainer;
    }
}
