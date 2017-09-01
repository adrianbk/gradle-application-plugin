package com.adrianbk.deployment.model;

public final class Application {
    private final String name;
    private final Distribution distribution;
    private final Iterable<ApplicationEnvironment> environments;

    Application(ApplicationBuilder builder) {
        this.name = builder.name;
        this.distribution = builder.distribution;
        this.environments = builder.environments;
    }

    public String getName() {
        return name;
    }

    public Distribution getDistribution() {
        return distribution;
    }

    public Iterable<ApplicationEnvironment> getEnvironments() {
        return environments;
    }

}
