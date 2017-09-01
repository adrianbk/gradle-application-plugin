package com.adrianbk.deployment.model;

public class ApplicationEnvironment {
    private final String name;
    private final Iterable<Deployment> deployments;

    ApplicationEnvironment(String name, Iterable<Deployment> deployments) {
        this.name = name;
        this.deployments = deployments;
    }

    public String getName() {
        return name;
    }

    public Iterable<Deployment> getDeployments() {
        return deployments;
    }

}
