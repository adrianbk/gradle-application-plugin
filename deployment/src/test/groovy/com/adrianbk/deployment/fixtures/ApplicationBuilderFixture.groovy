package com.adrianbk.deployment.fixtures

import com.adrianbk.deployment.model.ApplicationBuilder

import static com.adrianbk.deployment.model.ApplicationEnvironmentBuilder.createEnvironment

class ApplicationBuilderFixture {
    static ApplicationBuilder appWithSingleEnvironment() {
        ApplicationBuilder.application("sample-app").environment(createEnvironment("development").build())
    }
}
