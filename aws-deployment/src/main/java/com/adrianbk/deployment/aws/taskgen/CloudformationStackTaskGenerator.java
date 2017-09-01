package com.adrianbk.deployment.aws.taskgen;

import com.adrianbk.deployment.aws.model.CloudFormationStack;
import com.adrianbk.deployment.aws.model.S3Distribution;
import com.adrianbk.deployment.model.ApplicationEnvironment;
import com.adrianbk.deployment.taskgen.InfrastructureTaskGenerator;
import org.gradle.api.tasks.TaskContainer;

public class CloudformationStackTaskGenerator implements InfrastructureTaskGenerator<S3Distribution, CloudFormationStack> {

    @Override
    public void generate(TaskContainer taskContainer, S3Distribution distribution, CloudFormationStack infrastructureComponent, ApplicationEnvironment applicationEnvironment) {

    }
}
