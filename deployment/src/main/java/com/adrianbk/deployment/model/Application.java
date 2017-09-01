package com.adrianbk.deployment.model;

import com.adrianbk.deployment.assertions.Assert;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public final class Application {
    private final String name;
    private final Distribution distribution;
    private final Iterable<ApplicationEnvironment> environments;

    private Application(Builder builder) {
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

    static class Builder {
        private final String name;
        private Distribution distribution;

        private List<ApplicationEnvironment> environments = new ArrayList<>();

        private Builder(String name) {
            this.name = name;
        }

        public static Builder of(String name) {
            return new Builder(name);
        }

        public Builder distribution(Distribution distribution) {
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

        public Builder environment(ApplicationEnvironment environment) {
            environments.add(environment);
            return this;
        }
    }
}
