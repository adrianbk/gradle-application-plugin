package com.adrianbk.deployment.taskgen;

import com.adrianbk.deployment.model.Distribution;
import com.adrianbk.deployment.model.InfrastructureComponent;

public interface InfrastructureTaskGenerator<D extends Distribution, C extends InfrastructureComponent> {
    Class<D> getDistributionType();

    Class<C> getInfrastructureComponent();

    void generate(InfrastructureTaskGenerationSpec<D, C> taskGenerationSpec);
}
