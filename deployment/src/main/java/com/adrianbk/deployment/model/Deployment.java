package com.adrianbk.deployment.model;

public class Deployment {
    private final Distribution distribution;
    private final Iterable<InfrastructureComponent> infrastructureComponents;

    Deployment(DeploymentBuilder deploymentBuilder) {
        this.distribution = deploymentBuilder.getDistribution();
        this.infrastructureComponents = deploymentBuilder.getInfrastructureComponents();
    }

    public Distribution getDistribution() {
        return distribution;
    }

    public Iterable<InfrastructureComponent> getInfrastructureComponents() {
        return infrastructureComponents;
    }
}
