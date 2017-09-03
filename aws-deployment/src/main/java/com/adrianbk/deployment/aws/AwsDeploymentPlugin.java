package com.adrianbk.deployment.aws;

import com.adrianbk.deployment.aws.internal.taskgen.CloudFormationStackTaskGenerator;
import com.adrianbk.deployment.plugin.DeploymentPlugin;
import com.adrianbk.deployment.plugin.DeploymentSpec;
import org.gradle.api.Plugin;
import org.gradle.api.Project;

import java.util.Collections;

public class AwsDeploymentPlugin implements Plugin<Project> {

    @Override
    public void apply(Project project) {
        if (project.getPlugins().findPlugin(DeploymentPlugin.class) == null) {
            project.getPlugins().apply(DeploymentPlugin.class);
        }

        DeploymentPlugin deploymentPlugin = project.getPlugins().findPlugin(DeploymentPlugin.class);

        deploymentPlugin.register(new DeploymentSpec(Collections.singletonList(new CloudFormationStackTaskGenerator())));
    }
}
