package com.adrianbk.deployment.model.config;

public abstract class ConfigurationValuesDecorator implements ConfigurationValues {
    protected ConfigurationValues baseParameterProvider;

    public ConfigurationValuesDecorator(ConfigurationValues baseParameterProvider) {
        this.baseParameterProvider = baseParameterProvider;
    }
}
