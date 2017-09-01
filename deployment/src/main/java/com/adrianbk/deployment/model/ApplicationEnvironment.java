package com.adrianbk.deployment.model;

public class ApplicationEnvironment {
    private final String name;
    private final Iterable<Deployment> deployments;

    public ApplicationEnvironment(Builder builder) {
        this.name = builder.name;
        this.deployments = builder.deployments;
    }

    public String getName() {
        return name;
    }

    public Iterable<Deployment> getDeployment() {
        return deployments;
    }

    static class Builder {
        private final String name;
        private Iterable<Deployment> deployments;

        private Builder(String name) {
            this.name = name;
        }

        static Builder of(String name) {
            return new Builder(name);
        }

        ApplicationEnvironment build() {
            return new ApplicationEnvironment(this);
        }
    }
}
