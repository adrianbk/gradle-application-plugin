package com.adrianbk.deployment.plugin;

import com.adrianbk.deployment.model.Distribution;
import com.adrianbk.deployment.model.InfrastructureComponent;
import com.adrianbk.deployment.taskgen.InfrastructureTaskGenerationSpec;
import com.adrianbk.deployment.taskgen.InfrastructureTaskGenerator;
import org.gradle.api.Plugin;
import org.gradle.api.Project;

public final class DeploymentPlugin implements Plugin<Project> {

    private final Register register = new Register();

    @Override
    public void apply(Project project) {
        DeploymentExtension deploymentExtension = project.getExtensions().create("deployment", DeploymentExtension.class);
        project.afterEvaluate(p -> deploymentExtension.getApplications().forEach(a -> a.getEnvironments().forEach(e -> {
            e.getDeployments().forEach((d) -> d.getInfrastructureComponents().forEach((ic) -> {
                Iterable<InfrastructureTaskGenerator<? extends Distribution, ? extends InfrastructureComponent>> infrastructureTaskGenerators = register.getInfrastructureTaskGenerators(d.getDistribution().getClass(), ic.getClass());
                infrastructureTaskGenerators.forEach(gen -> {
                    gen.generate(new InfrastructureTaskGenerationSpec(d.getDistribution(), ic, e, p.getTasks()));
                });
            }));
        })));
    }

    public void register(DeploymentSpec deploymentSpec) {
        deploymentSpec.getInfrastructureTaskGenerators().forEach(register::registerInfrastructureTaskGenerator);
    }

}
