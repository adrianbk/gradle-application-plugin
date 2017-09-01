package com.adrianbk.deployment.fixtures

import com.adrianbk.deployment.model.ApplicationBuilder
import com.adrianbk.deployment.model.ApplicationEnvironmentBuilder

class ApplicationBuilderFixture {
    static ApplicationBuilder appWithSingleEnvironment() {
        ApplicationBuilder.application("sample-app").environment(ApplicationEnvironmentBuilder.of("development").build())
    }
}
