package com.adrianbk.deployment.plugin;

import com.adrianbk.deployment.model.Application;
import org.gradle.api.Project;
import org.gradle.api.provider.PropertyState;
import org.gradle.api.provider.Provider;

import java.util.List;

public class DeploymentExtension {

    private final PropertyState<List<Application>> applicationState;

    public DeploymentExtension(Project project) {
        this.applicationState = (PropertyState<List<Application>>) (Object) project.property(List.class);
    }

    public Provider<List<Application>> getApplicationsProvider() {
        return applicationState;
    }

    public List<Application> getApplications() {
        return applicationState.get();
    }

    public void setApplications(List<Application> applications) {
        applicationState.set(applications);
    }
}
