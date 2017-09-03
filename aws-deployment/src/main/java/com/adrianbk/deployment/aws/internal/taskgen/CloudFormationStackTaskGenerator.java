package com.adrianbk.deployment.aws.internal.taskgen;

import com.adrianbk.deployment.aws.model.CloudFormationStack;
import com.adrianbk.deployment.aws.model.S3Distribution;
import com.adrianbk.deployment.taskgen.InfrastructureTaskGenerationSpec;
import com.adrianbk.deployment.taskgen.InfrastructureTaskGenerator;
import org.gradle.api.Action;
import org.gradle.api.Task;

public class CloudFormationStackTaskGenerator implements InfrastructureTaskGenerator<S3Distribution, CloudFormationStack> {

    @Override
    public Class<S3Distribution> getDistributionType() {
        return S3Distribution.class;
    }

    @Override
    public Class<CloudFormationStack> getInfrastructureComponent() {
        return CloudFormationStack.class;
    }

    @Override
    public void generate(InfrastructureTaskGenerationSpec taskGenerationSpec) {
        taskGenerationSpec.getTaskContainer().create("someTask", new Action<Task>() {
            @Override
            public void execute(Task task) {
                task.setGroup("group");
                task.setDescription("some description");
            }
        });
    }
}
