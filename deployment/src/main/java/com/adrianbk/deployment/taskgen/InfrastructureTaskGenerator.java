package com.adrianbk.deployment.taskgen;

import com.adrianbk.deployment.model.ApplicationEnvironment;
import com.adrianbk.deployment.model.Distribution;
import com.adrianbk.deployment.model.InfrastructureComponent;
import org.gradle.api.tasks.TaskContainer;

public interface InfrastructureTaskGenerator<D extends Distribution, C extends InfrastructureComponent> {

    void generate(TaskContainer taskContainer, D distribution, C infrastructureComponent, ApplicationEnvironment applicationEnvironment);
}
