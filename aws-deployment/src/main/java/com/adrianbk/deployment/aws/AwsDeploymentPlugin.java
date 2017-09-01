package com.adrianbk.deployment.aws;

import com.adrianbk.deployment.plugin.DeploymentPlugin;
import org.gradle.api.Plugin;
import org.gradle.api.Project;

public class AwsDeploymentPlugin implements Plugin<Project> {
    @Override
    public void apply(Project project) {
        if (project.getPlugins().findPlugin(DeploymentPlugin.class) == null) {
            project.getPlugins().apply(DeploymentPlugin.class);
        }
    }
}
