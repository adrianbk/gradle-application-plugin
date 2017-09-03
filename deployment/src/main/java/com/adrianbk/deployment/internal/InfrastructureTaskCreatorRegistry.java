package com.adrianbk.deployment.internal;

import com.adrianbk.deployment.assertions.Assert;
import com.adrianbk.deployment.model.Distribution;
import com.adrianbk.deployment.model.InfrastructureComponent;
import com.adrianbk.deployment.taskgen.InfrastructureTaskGenerator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;


public class InfrastructureTaskCreatorRegistry {

    private Map<CompositeKey<? extends Distribution, ? extends InfrastructureComponent>, List<InfrastructureTaskGenerator<? extends Distribution, ? extends InfrastructureComponent>>> store = new HashMap<>();

    public <D extends Distribution, C extends InfrastructureComponent, I extends InfrastructureTaskGenerator<D, C>> void register(I infrastructureTaskGenerator) {
        Class<D> distributionType = infrastructureTaskGenerator.getDistributionType();
        Class<C> infrastructureComponent = infrastructureTaskGenerator.getInfrastructureComponent();

        Assert.notNull(distributionType, "distributionType");
        Assert.notNull(infrastructureComponent, "infrastructureComponent");

        CompositeKey<D, C> compositeKey = new CompositeKey<>(distributionType, infrastructureComponent);

        if (store.containsKey(compositeKey)) {
            store.get(compositeKey).add(infrastructureTaskGenerator);
        } else {
            List<InfrastructureTaskGenerator<? extends Distribution, ? extends InfrastructureComponent>> generators = new ArrayList<>();
            generators.add(infrastructureTaskGenerator);
            store.put(compositeKey, generators);
        }
    }

    public <D extends Distribution, C extends InfrastructureComponent> Iterable<InfrastructureTaskGenerator<? extends Distribution, ? extends InfrastructureComponent>> generatorsByTypes(Class<D> distributionType, Class<C> infrastructureComponent) {
        CompositeKey<D, C> compositeKey = new CompositeKey<>(distributionType, infrastructureComponent);
        if (!store.containsKey(compositeKey)) {
            return Collections.emptyList();
        }
        return store.get(compositeKey);
    }

    static class CompositeKey<D extends Distribution, C extends InfrastructureComponent> {
        private final Class<D> distClass;
        private final Class<C> infraClass;

        CompositeKey(Class<D> distClass, Class<C> infraClass) {
            this.distClass = distClass;
            this.infraClass = infraClass;
        }

        @Override
        public int hashCode() {
            return Objects.hash(distClass, infraClass);
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            final CompositeKey other = (CompositeKey) obj;
            return Objects.equals(this.distClass, other.distClass)
                    && Objects.equals(this.infraClass, other.infraClass);
        }
    }
}
