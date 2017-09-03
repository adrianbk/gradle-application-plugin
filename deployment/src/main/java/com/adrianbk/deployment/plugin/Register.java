package com.adrianbk.deployment.plugin;

import com.adrianbk.deployment.internal.InfrastructureTaskCreatorRegistry;
import com.adrianbk.deployment.model.Distribution;
import com.adrianbk.deployment.model.InfrastructureComponent;
import com.adrianbk.deployment.taskgen.InfrastructureTaskGenerator;

final class Register {

    private final InfrastructureTaskCreatorRegistry infrastructureTaskCreatorRegistry = new InfrastructureTaskCreatorRegistry();

    void registerInfrastructureTaskGenerator(InfrastructureTaskGenerator<? extends Distribution, ? extends InfrastructureComponent> infrastructureTaskGenerator) {
        infrastructureTaskCreatorRegistry.register(infrastructureTaskGenerator);
    }

    public <D extends Distribution, C extends InfrastructureComponent> Iterable<InfrastructureTaskGenerator<? extends Distribution, ? extends InfrastructureComponent>> getInfrastructureTaskGenerators(Class<D> distributionType, Class<C> infrastructureComponent) {
        return infrastructureTaskCreatorRegistry.generatorsByTypes(distributionType, infrastructureComponent);
    }
}
