package com.adrianbk.deployment.model;

import java.util.ArrayList;
import java.util.List;

public class DeploymentBuilder {
    private final Distribution distribution;
    private final List<InfrastructureComponent> infrastructureComponents;

    private DeploymentBuilder(Distribution distribution) {
        this.distribution = distribution;
        this.infrastructureComponents = new ArrayList<>();
    }

    public static DeploymentBuilder of(Distribution distribution) {
        return new DeploymentBuilder(distribution);
    }

    public DeploymentBuilder infrastructure(InfrastructureComponent infrastructureComponent) {
        this.infrastructureComponents.add(infrastructureComponent);
        return this;
    }

    public Deployment build() {
        return new Deployment(this);
    }

    public Distribution getDistribution() {
        return distribution;
    }

    public Iterable<InfrastructureComponent> getInfrastructureComponents() {
        return infrastructureComponents;
    }


}
