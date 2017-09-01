package com.adrianbk.deployment.model;

import com.adrianbk.deployment.assertions.Assert;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ApplicationBuilder {
    final String name;
    Distribution distribution;

    List<ApplicationEnvironment> environments = new ArrayList<>();

    private ApplicationBuilder(String name) {
        this.name = name;
    }

    public static ApplicationBuilder application(String name) {
        return new ApplicationBuilder(name);
    }

    public ApplicationBuilder distribution(Distribution distribution) {
        this.distribution = distribution;
        return this;
    }

    public Application build() {
        validate();
        return new Application(this);
    }

    private void validate() {
        Map<String, List<ApplicationEnvironment>> map = this.environments.stream().collect(Collectors.groupingBy(ApplicationEnvironment::getName));
        checkForDistribution();
        checkApplication();
        checkForDuplicateEnvironments(map);

    }

    private void checkForDistribution() {
        Assert.notNull(distribution, "distribution");
    }

    private void checkApplication() {
        Assert.notNullOrBlank(name, "application name");
    }

    private void checkForDuplicateEnvironments(Map<String, List<ApplicationEnvironment>> map) {
        map.forEach((k, v) -> {
            if (v.size() > 1) {
                throw new RuntimeException(String.format("There cannot be more than one environment with the name '%s'", k));
            }
        });
    }

    public ApplicationBuilder environment(ApplicationEnvironment environment) {
        environments.add(environment);
        return this;
    }
}
