package com.adrianbk.deployment.model.config;

import java.util.Map;

public class MapConfigurationValues implements ConfigurationValues {
    private final Map<String, String> map;

    public MapConfigurationValues(Map<String, String> map) {
        this.map = map;
    }

    @Override
    public Map<String, String> get() {
        return map;
    }
}
