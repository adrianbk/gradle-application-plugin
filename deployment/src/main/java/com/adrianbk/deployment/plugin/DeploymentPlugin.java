package com.adrianbk.deployment.plugin;

import org.gradle.api.Plugin;
import org.gradle.api.Project;

public class DeploymentPlugin implements Plugin<Project> {
    @Override
    public void apply(Project project) {
        project.getExtensions().create("deployment", DeploymentExtension.class, project);
    }
}
