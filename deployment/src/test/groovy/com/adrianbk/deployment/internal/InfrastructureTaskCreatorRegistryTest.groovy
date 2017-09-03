package com.adrianbk.deployment.internal

import com.adrianbk.deployment.model.Distribution
import com.adrianbk.deployment.model.InfrastructureComponent
import com.adrianbk.deployment.taskgen.InfrastructureTaskGenerator
import com.adrianbk.deployment.taskgen.InfrastructureTaskGenerationSpec
import spock.lang.Specification
import spock.lang.Unroll

class InfrastructureTaskCreatorRegistryTest extends Specification {

    def "can register a task generator"() {
        given:
          InfrastructureTaskCreatorRegistry ir = new InfrastructureTaskCreatorRegistry()
          InfrastructureTaskGeneratorImpl<SomeDistType, SomeInfraType> testGen = new InfrastructureTaskGeneratorImpl<>()
          ir.register(testGen)

        expect:
          List<InfrastructureTaskGenerator<SomeDistType, SomeInfraType>> generators = ir.generatorsByTypes(SomeDistType, SomeInfraType)
          generators.size() == 1
          generators[0] == testGen
    }

    def "can register more than one task generator"() {
        given:
          InfrastructureTaskCreatorRegistry ir = new InfrastructureTaskCreatorRegistry()
          InfrastructureTaskGeneratorImpl<SomeDistType, SomeInfraType> testGen = new InfrastructureTaskGeneratorImpl<>()
          ir.register(testGen)
          ir.register(testGen)

        expect:
          List<InfrastructureTaskGenerator<SomeDistType, SomeInfraType>> generators = ir.generatorsByTypes(SomeDistType, SomeInfraType)
          generators.size() == 2
          generators[0] == testGen
          generators[1] == testGen
    }

    def "Returns empty for unknown"() {
        given:
          InfrastructureTaskCreatorRegistry ir = new InfrastructureTaskCreatorRegistry()
        expect:
          List<InfrastructureTaskGenerator<SomeDistType, SomeInfraType>> generators = ir.generatorsByTypes(SomeDistType, SomeInfraType)
          generators.size() == 0
    }

    @Unroll
    def "Can not register null types"() {
        given:
          InfrastructureTaskCreatorRegistry ir = new InfrastructureTaskCreatorRegistry()
          def distType = dType
          def infraType = iType
        when:
          ir.register(new InfrastructureTaskGenerator<SomeDistType, SomeInfraType>() {

              @Override
              Class<SomeDistType> getDistributionType() {
                  return distType
              }

              @Override
              Class<SomeInfraType> getInfrastructureComponent() {
                  return infraType
              }

              @Override
              void generate(InfrastructureTaskGenerationSpec<SomeDistType, SomeInfraType> taskGenerationSpec) {

              }
          })

        then:
          def ex = thrown(IllegalArgumentException)
          ex.message == expectedMessage

        where:
          dType        | iType         || expectedMessage
          null         | SomeInfraType || "'distributionType' must not be null"
          SomeDistType | null          || "'infrastructureComponent' must not be null"
    }

    private class SomeInfraType implements InfrastructureComponent {
    }

    private class SomeDistType implements Distribution {
        @Override
        String getVersion() {
            return null
        }
    }

    private class InfrastructureTaskGeneratorImpl<D extends Distribution, C extends InfrastructureComponent> implements InfrastructureTaskGenerator<D, C> {
        @Override
        Class<D> getDistributionType() {
            return SomeDistType
        }

        @Override
        Class<C> getInfrastructureComponent() {
            return SomeInfraType
        }

        @Override
        void generate(InfrastructureTaskGenerationSpec<D, C> taskGenerationSpec) {

        }
    }
}
