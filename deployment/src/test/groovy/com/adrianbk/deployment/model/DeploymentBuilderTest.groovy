package com.adrianbk.deployment.model

import spock.lang.Specification

class DeploymentBuilderTest extends Specification {

    def "can have multiple deployments"() {
        expect:
          DeploymentBuilder.deployment(Mock(Distribution))
                  .infrastructure(Mock(InfrastructureComponent))
                  .infrastructure(Mock(InfrastructureComponent))
                  .build().getInfrastructureComponents()
                  .spliterator().getExactSizeIfKnown() == 2
    }
}
