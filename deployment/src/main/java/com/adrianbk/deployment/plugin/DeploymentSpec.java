package com.adrianbk.deployment.plugin;

import com.adrianbk.deployment.model.Distribution;
import com.adrianbk.deployment.model.InfrastructureComponent;
import com.adrianbk.deployment.taskgen.InfrastructureTaskGenerator;

public class DeploymentSpec {
    private final Iterable<InfrastructureTaskGenerator<? extends Distribution, ? extends InfrastructureComponent>> infrastructureTaskGenerators;

    public DeploymentSpec(Iterable<InfrastructureTaskGenerator<? extends Distribution, ? extends InfrastructureComponent>> infrastructureTaskGenerators) {
        this.infrastructureTaskGenerators = infrastructureTaskGenerators;
    }

    public Iterable<InfrastructureTaskGenerator<? extends Distribution, ? extends InfrastructureComponent>> getInfrastructureTaskGenerators() {
        return infrastructureTaskGenerators;
    }
}
