package com.adrianbk.deployment.model;

import java.util.ArrayList;
import java.util.List;

public final class ApplicationEnvironmentBuilder {
    private final String name;
    private List<Deployment> deployments;

    private ApplicationEnvironmentBuilder(String name) {
        this.name = name;
        this.deployments = new ArrayList<>();
    }

    public static ApplicationEnvironmentBuilder of(String name) {
        return new ApplicationEnvironmentBuilder(name);
    }

    public ApplicationEnvironmentBuilder deployment(Deployment deployment) {
        this.deployments.add(deployment);
        return this;
    }

    public ApplicationEnvironment build() {
        return new ApplicationEnvironment(name, deployments);
    }
}
